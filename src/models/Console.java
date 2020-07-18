package models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
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

import enums.Action;
import helpers.AdventureDeserializer;
import models.parser.Command;
import models.parser.LookAt;
import models.parser.Move;
import models.parser.Parser;
import models.parser.Pickup;
import models.parser.Save;
import models.parser.TalkTo;
import models.parser.Use;

public class Console extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane console;
	private JTextField input;
	private JScrollPane scrollpane;
	private DrawPanel gameGraphics;
	private JToolBar toolbar;

	private ArrayList<String> recent_used;
	private int recent_used_id = 0;
	private int recent_used_max = 10;

	private Adventure selectedAdventure;
	private Parser parser;

	public Console() {
		parser = new LookAt();
		parser.linkWith(new Move()).linkWith(new Pickup()).linkWith(new Save()).linkWith(new TalkTo())
				.linkWith(new Use());

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		
		toolbar = new JToolBar();
		gameGraphics = new DrawPanel();
		
		setTitle("Console");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		toolbar.setSize(1000,50);
		toolbar.setBackground(Color.YELLOW);
		
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		console.setForeground(Color.WHITE);
		console.setSize(1000, 200);
		console.getStyledDocument();

		scrollpane = new JScrollPane();
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);

		recent_used = new ArrayList<String>();
		recent_used_id = 0;
		recent_used_max = 10;

		console.setEditable(false);
		console.setOpaque(false);
		scrollpane.setViewportView(console);
		scrollpane.getViewport().setOpaque(false);

		input = new JTextField();
		input.setEditable(true);
		input.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		input.setSize(1000,50);
		input.setCaretColor(Color.WHITE);
		input.setForeground(Color.WHITE);
		input.setOpaque(false);

		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String text = input.getText();
				print(text);

				recent_used.add(text);
				recent_used_id = 0;

				Command comando = Parser.parse(text);

				if (comando != null) {
					selectedAdventure.appendRequest(text);
					String result = parser.execute(selectedAdventure, comando);
					selectedAdventure.appendResponse(result);
					final char[] var = { '.', '\n' };
					print(WordUtils.capitalizeFully(result, var));

					if (selectedAdventure.isEnded()) {
						if (selectedAdventure.getSelectedPlayer().getHP() <= 0) {
							print("Te moriste. Mejor suerte la proxima ...");
						} else {
							print("Felicidades! has llegado al final. Te esperamos en tu proxima aventura.");
						}

						int dialogResult = JOptionPane.showConfirmDialog(null,
								"\r\n¿Desea guardar su recorrido en la aventura?\r\n", "Khoeli",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
						Date date = new Date(System.currentTimeMillis());
						if (dialogResult == JOptionPane.YES_OPTION) {
							parser.execute(selectedAdventure, new Command(Action.SAVE.toString(),
									selectedAdventure.getSelectedPlayer().getName() + " - " + formatter.format(date),
									null));
						}
						input.setEnabled(false);
					}
				} else {
					print("Acción incorrecta. Intente nuevamente.");
				}

			}

		});

		input.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {

					if (recent_used_id < (recent_used_max - 1) && (recent_used_id < (recent_used.size() - 1))) {
						recent_used_id++;
					}

					int cant = recent_used.size() - 1 - recent_used_id;
					if (cant >= 0 && cant < recent_used.size()) {
						input.setText(recent_used.get(cant));
					}

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (recent_used_id > 0) {
						recent_used_id--;
					}

					int cant = recent_used.size() - 1 - recent_used_id;
					if (cant >= 0 && cant < recent_used.size()) {
						input.setText(recent_used.get(cant));
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
		});

		add(toolbar, BorderLayout.NORTH);
		add(input, BorderLayout.SOUTH);
		add(scrollpane, BorderLayout.CENTER);
		add(gameGraphics, BorderLayout.NORTH);
		

		getContentPane().setBackground(new Color(52, 52, 52));
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);

	}

	private void print(String text) {

		if (text.length() > 0) {
			Document doc = console.getDocument();
			try {
				doc.insertString(doc.getLength(), text + "\n", null);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			scrollBottom();
			input.setText("");

		}
		gameGraphics.repaint();
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
		print("Bienvenido a Khoeli!\r\nPuedes usar los siguientes comandos:\r\n-IR\r\n-MIRAR\r\n-HABLAR\r\n-USAR\r\n");
		print("Ingrese su nombre (si no ingresa nada, el nombre será " + selectedAdventure.getSelectedPlayer().getName()
				+ "):");


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

	private class DrawPanel extends JPanel {
		private static final long serialVersionUID = 91574813372177663L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
//			Dimension currentDimension = new Dimension(800, 600);
//			g2.scale(currentDimension.getWidth() / 800, currentDimension.getHeight() / 450);

			if (selectedAdventure != null) {
				Sprite locationSprite = selectedAdventure.getSelectedPlayer().getCurrentLocation().getSprite();
				if (locationSprite != null) {
					g2.drawImage(locationSprite.getImage(), locationSprite.getX(), locationSprite.getY(),
							locationSprite.getWidth(), locationSprite.getHeight(), null);
				}

				for (Place place : selectedAdventure.getSelectedPlayer().getCurrentLocation().getPlaces()) {
					Sprite placeSprite = place.getSprite();
					if (placeSprite != null) {
						g2.drawImage(placeSprite.getImage(), placeSprite.getX(), placeSprite.getY(),
								placeSprite.getWidth(), placeSprite.getHeight(), null);
					}

					for (Item item : place.getItems()) {
						Sprite itemSprite = item.getSprite();
						if (itemSprite != null) {
							g2.drawImage(itemSprite.getImage(), itemSprite.getX(), itemSprite.getY(),
									itemSprite.getWidth(), itemSprite.getHeight(), null);
						}

					}
				}
				
				for (NonPlayable npc : selectedAdventure.getSelectedPlayer().getCurrentLocation().getNpcs()) {
					Sprite npcSprite = npc.getSprite();
					if (npcSprite != null) {
						g2.drawImage(npcSprite.getImage(), npcSprite.getX(), npcSprite.getY(),
								npcSprite.getWidth(), npcSprite.getHeight(), null);
					}

				}
			}

		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1000, 450);
		}
	}

}
