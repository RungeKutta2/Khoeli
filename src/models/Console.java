package models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.commons.text.WordUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import helpers.AdventureDeserializer;
import models.parser.Command;
import models.parser.LookAt;
import models.parser.Move;
import models.parser.Parser;
import models.parser.Pickup;
import models.parser.Save;
import models.parser.TalkTo;
import models.parser.Use;

public class Console extends JFrame{
	private JTextPane console;
	private JTextField input;
	private JScrollPane scrollpane;
	private StyledDocument document;
	private boolean trace;
	
	private ArrayList<String> recent_used;
	private int recent_used_id = 0;
	private int recent_used_max = 10;
	
	private Adventure selectedAdventure;
	private Parser parser;
	
	public Console() {
		parser = new LookAt();
		parser.linkWith(new Move())
			  .linkWith(new Pickup())
			  .linkWith(new Save())
			  .linkWith(new TalkTo())
			  .linkWith(new Use());
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		setTitle("Console");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		trace=false;
		
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Bookman Old Style" ,Font.PLAIN, 14));
		console.setForeground(Color.WHITE);
		
		document = console.getStyledDocument();
		
		scrollpane = new JScrollPane();
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);
		
		recent_used = new ArrayList<String>();
		recent_used_id=0;
		recent_used_max=10;
		
		console.setEditable(false);
		console.setOpaque(false);
		scrollpane.setViewportView(console);
		scrollpane.getViewport().setOpaque(false);
		
		input = new JTextField();
		input.setEditable(true);
		input.setCaretColor(Color.WHITE);
		input.setForeground(Color.WHITE);
		input.setOpaque(false);
		
		
		
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text	= input.getText();
				print(text);
				
				Command comando = Parser.parse(text);

				if (comando != null) {
					selectedAdventure.appendRequest(text);
					String result = parser.execute(selectedAdventure, comando);
					selectedAdventure.appendResponse(result);
					final char[] var = { '.', '\n' };
					print(WordUtils.capitalizeFully(result, var));
				}
			}

			
		});
		
		input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					
					
					if(recent_used_id < (recent_used_max - 1) && (recent_used_id < (recent_used.size() - 1))) {
						recent_used_id++;
					}
					
					int cant = recent_used.size() - 1 - recent_used_id;
					if(cant >= 0 && cant < recent_used.size()) {
						input.setText(recent_used.get(cant));
					}
					
				}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					if(recent_used_id > 0) {
						recent_used_id--;
					}
					
					int cant = recent_used.size() - 1 - recent_used_id;
					if(cant >= 0 && cant < recent_used.size()) {
						input.setText(recent_used.get(cant));
					}
				}
				
			}
		});
		
		
		add(input, BorderLayout.SOUTH);
		add(scrollpane, BorderLayout.CENTER);
		
		getContentPane().setBackground(new Color(52,52,52));
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		

		
		
	}
	
	private void print(String text) {
		
		
		if(text.length() > 0) {
			recent_used.add(text);
			recent_used_id=0;
			Document doc = console.getDocument();
			try {
				doc.insertString(doc.getLength(),text + "\n",null);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			scrollBottom();
			input.setText("");
			
		}
	}
	
	public void setSelectedAdventure(String filename) throws IOException {
		Gson gson = new Gson();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Adventure.class, new AdventureDeserializer());
		gson = gsonBuilder.create();
		Reader reader = Files.newBufferedReader(Paths.get(filename));
		selectedAdventure = gson.fromJson(reader, Adventure.class);
		reader.close();
	}
	
	public void selectAdventure() {
		
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

		JFrame ventana = new JFrame(); 
		
		
		selectorArchivos.setCurrentDirectory(new File("./aventuras/"));
		selectorArchivos.showOpenDialog(null);
		if (selectorArchivos.getSelectedFile() == null) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame,
					"No seleccionó ninguna aventura! Ejecute de nuevo el programa y seleccione una aventura válida.",
					"Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		File archivo = selectorArchivos.getSelectedFile();

		try {
			setSelectedAdventure(archivo.getPath());
		} catch (IOException e) {
			System.err.println("archivo de aventura invalido");
		}
	}
	
	public void startAdventure() {
		print(
				"Bienvenido a Khoeli!\r\nPuedes usar los siguientes comandos:\r\n-IR\r\n-MIRAR\r\n-HABLAR\r\n-USAR\r\n");
		print("Ingrese su nombre (si no ingresa nada, el nombre será "
				+ selectedAdventure.getSelectedPlayer().getName() + "):");

		//String name = scanner.nextLine();
//		if (!name.isEmpty()) {
//			selectedAdventure.getSelectedPlayer().setName(name);
//		}
		
		print(selectedAdventure.getWelcomeMessage());
		print("\n");
		print(selectedAdventure.getSelectedPlayer().getCurrentLocation().getDescription());
	}
	
	public void scrollTop() {
		console.setCaretPosition(0);
	}
	
	public void scrollBottom() {
		console.setCaretPosition(console.getDocument().getLength());
	}
	
	
}
