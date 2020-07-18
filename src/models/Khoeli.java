package models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
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

public class Khoeli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextPane console;
	private JTextField input;
	private JScrollPane scrollpane;
	private DrawPanel gameGraphics;

	private JToolBar toolbar;
	
	private JButton buttonToolbarSaveAdventure;
	private JButton buttonToolbarOpenAdventure;
	private JButton buttonToolbarInventary;
	private JButton buttonToolbarMap;
	
	private InventoryFrame frameInventory;
	private JFrame frameMap;
	

	private ArrayList<String> recent_used;
	private int recent_used_id = 0;
	private int recent_used_max = 10;

	private Adventure selectedAdventure;
	private Parser parser;

	public Khoeli() {
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

		setTitle("Khoeli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonToolbarSaveAdventure = new JButton();
		buttonToolbarSaveAdventure.setText("Guardar");
		buttonToolbarSaveAdventure.setBorderPainted(false);
		buttonToolbarSaveAdventure.setFocusPainted(false);
		buttonToolbarSaveAdventure.setMargin(new Insets(5, 10, 5, 10));
		buttonToolbarSaveAdventure.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonToolbarSaveAdventure.setEnabled(false);

		buttonToolbarSaveAdventure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveAdventure();
			}
		});

		buttonToolbarOpenAdventure = new JButton();
		buttonToolbarOpenAdventure.setText("Seleccionar aventura");
		buttonToolbarOpenAdventure.setBorderPainted(false);
		buttonToolbarOpenAdventure.setFocusPainted(false);
		buttonToolbarOpenAdventure.setMargin(new Insets(5, 10, 5, 10));
		buttonToolbarOpenAdventure.setFont(new Font("Arial", Font.PLAIN, 12));

		buttonToolbarOpenAdventure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectAdventure();
			}
		});

		buttonToolbarInventary = new JButton();
		buttonToolbarInventary.setText("Inventario");
		buttonToolbarInventary.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonToolbarInventary.setBorderPainted(false);
		buttonToolbarInventary.setFocusPainted(false);
		buttonToolbarInventary.setMargin(new Insets(5, 10, 5, 10));
		buttonToolbarInventary.setEnabled(false);
		buttonToolbarInventary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (frameInventory == null) {
					frameInventory = new InventoryFrame();
					frameInventory.setSize(300, 400);
					frameInventory.setResizable(false);
					frameInventory.setInventory(selectedAdventure.getSelectedPlayer().getInventory());
					frameInventory.setTitle("Inventario");
					frameInventory.setVisible(true);

					frameInventory.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							frameInventory = null;

						}

						@Override
						public void windowClosed(WindowEvent e) {

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
				} else {
					frameInventory.setState(Frame.NORMAL);
				}

			}
		});

		buttonToolbarMap = new JButton();
		buttonToolbarMap.setText("Mapa");
		buttonToolbarMap.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonToolbarMap.setBorderPainted(false);
		buttonToolbarMap.setFocusPainted(false);
		buttonToolbarMap.setMargin(new Insets(5, 10, 5, 10));
		buttonToolbarMap.setEnabled(false);
		buttonToolbarMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (frameMap == null) {
					frameMap = new JFrame();
					frameMap.add(new MapPanel());
					frameMap.setSize(995, 695);
					frameMap.setTitle("Mapa");
					frameMap.setResizable(false);
					frameMap.setVisible(true);

					frameMap.addWindowListener(new WindowListener() {

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void windowClosing(WindowEvent e) {
							frameMap = null;

						}

						@Override
						public void windowClosed(WindowEvent e) {

						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub

						}
					});
				} else {
					frameMap.setState(Frame.NORMAL);
				}
			}
		});

		toolbar.setSize(1000, 50);
		toolbar.setFloatable(false);
		toolbar.add(buttonToolbarOpenAdventure);
		toolbar.add(buttonToolbarSaveAdventure);
		toolbar.add(buttonToolbarMap);
		toolbar.add(buttonToolbarInventary);

		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		console.setForeground(Color.WHITE);
		console.setSize(980, 200);
		console.getStyledDocument();

		scrollpane = new JScrollPane();
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);
		scrollpane.setSize(980, 200);

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
		input.setSize(990, 50);
		input.setCaretColor(Color.WHITE);
		input.setForeground(Color.WHITE);
		input.setOpaque(false);
		input.setEnabled(false);

		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String text = input.getText();
				print(text);

				recent_used.add(text);
				recent_used_id = 0;

				Command comando = Parser.parse(text);

				selectedAdventure.appendRequest(text);
				if (comando != null) {

					String result = parser.execute(selectedAdventure, comando);
					selectedAdventure.appendResponse(result);
					final char[] var = { '.', '\n' };
					print(WordUtils.capitalizeFully(result, var));

					if (frameInventory != null) {
						frameInventory.setInventory(selectedAdventure.getSelectedPlayer().getInventory());
					}

					if (selectedAdventure.isEnded()) {
						if (selectedAdventure.getSelectedPlayer().getHP() <= 0) {
							print("Te moriste. Mejor suerte la proxima ...");
						} else {
							print("Felicidades! has llegado al final. Te esperamos en tu proxima aventura.");
						}

						input.setEnabled(false);
						buttonToolbarInventary.setEnabled(false);
						buttonToolbarMap.setEnabled(false);
						if (frameMap != null) {
							frameMap.setVisible(false);
						}
						if (frameInventory != null) {
							frameInventory.setVisible(false);
						}
					}
				} else {
					String result = "Acción incorrecta. Intente nuevamente.";
					print(result);
					selectedAdventure.appendResponse(result);
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

		setLayout(new GridBagLayout());

		GridBagConstraints toolbarConstraints = new GridBagConstraints();
		toolbarConstraints.gridx = 0;
		toolbarConstraints.gridy = 0;
		toolbarConstraints.ipadx = 1000;
		toolbarConstraints.anchor = GridBagConstraints.NORTH;
		add(toolbar, toolbarConstraints);

		GridBagConstraints gameGraphicsConstraints = new GridBagConstraints();
		gameGraphicsConstraints.gridx = 0;
		gameGraphicsConstraints.gridy = 1;
		gameGraphicsConstraints.ipadx = 1000;
		gameGraphicsConstraints.ipady = 440;
		gameGraphicsConstraints.anchor = GridBagConstraints.NORTH;
		add(gameGraphics, gameGraphicsConstraints);

		GridBagConstraints scrollpaneConstraints = new GridBagConstraints();
		scrollpaneConstraints.gridx = 0;
		scrollpaneConstraints.gridy = 2;
		scrollpaneConstraints.ipadx = 973;
		scrollpaneConstraints.ipady = 163;
		scrollpaneConstraints.anchor = GridBagConstraints.NORTH;
		add(scrollpane, scrollpaneConstraints);

		GridBagConstraints inputConstraints = new GridBagConstraints();
		inputConstraints.gridx = 0;
		inputConstraints.gridy = 3;
		inputConstraints.ipadx = 986;
		inputConstraints.anchor = GridBagConstraints.NORTH;
		add(input, inputConstraints);

		getContentPane().setBackground(new Color(52, 52, 52));
		setSize(1000, 720);
		setLocationRelativeTo(null);
		setResizable(false);

	}

	public static void main(String[] args) {

		File path = new File("./partidas guardadas/");
		if (!path.exists()) {
			path.mkdirs();
		}

		Khoeli console = new Khoeli();
		console.setVisible(true);

	}

	private void clear() {
		console.setText("");
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

	public void saveAdventure() {
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
		selectorArchivos.setCurrentDirectory(new File("./partidas guardadas/"));
		selectorArchivos.setDialogTitle("Guardar aventura");
		selectorArchivos.setFileFilter(new FileNameExtensionFilter("Archivo de texto", "TXT"));
		int result = selectorArchivos.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			parser.execute(selectedAdventure,
					new Command(Action.SAVE.toString(), selectorArchivos.getSelectedFile().getName(), null));
		}
	}

	public void selectAdventure() {

		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
		selectorArchivos.setDialogTitle("Seleccionar aventura");
		selectorArchivos.setCurrentDirectory(new File("./aventuras/"));
		selectorArchivos.setFileFilter(new FileNameExtensionFilter("JSON file", "JSON"));
		int result = selectorArchivos.showOpenDialog(null);

		if (result != JFileChooser.APPROVE_OPTION) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "No seleccionó ninguna aventura! Intente nuevamente.", "Error!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		File archivo = selectorArchivos.getSelectedFile();

		try {
			setSelectedAdventure(archivo.getPath());
			input.setEnabled(true);
			buttonToolbarSaveAdventure.setEnabled(true);
			buttonToolbarInventary.setEnabled(true);
			buttonToolbarMap.setEnabled(true);
			clear();
			startAdventure();

		} catch (IOException e) {
			System.err.println("archivo de aventura invalido");
		}
	}

	public void startAdventure() {
		print("Bienvenido a Khoeli!\r\nPuedes usar los siguientes comandos:\r\n-IR\r\n-MIRAR\r\n-HABLAR\r\n-USAR\r\n");

		String name = JOptionPane.showInputDialog("Ingrese su nombre (si no ingresa nada, el nombre será "
				+ selectedAdventure.getSelectedPlayer().getName() + "):");

		if (!name.isEmpty()) {
			selectedAdventure.getSelectedPlayer().setName(name);
		}
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
						g2.drawImage(npcSprite.getImage(), npcSprite.getX(), npcSprite.getY(), npcSprite.getWidth(),
								npcSprite.getHeight(), null);
					}

				}
			}

		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1000, 450);
		}
	}

	private class InventoryFrame extends JFrame {

		private static final long serialVersionUID = 91574813372177663L;
		private JScrollPane inventoryScrollpane;
		private JPanel inventoryPane;

		public InventoryFrame() {
			inventoryScrollpane = new JScrollPane();
			inventoryPane = new JPanel();

			inventoryScrollpane.setBorder(null);
			inventoryScrollpane.setOpaque(false);
			inventoryScrollpane.setSize(640, 480);
			inventoryScrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			inventoryScrollpane.setViewportView(inventoryPane);
			inventoryScrollpane.getViewport().setOpaque(false);
			inventoryPane.setSize(640, 480);

			add(inventoryScrollpane);

		}

		public void setInventory(Inventory items) {
			inventoryPane.removeAll();
			int cant = (int) Math.ceil(items.size() / 2.0);
			inventoryPane.setLayout(new GridLayout(cant, 2));

			for (Item item : items) {
				if (item.getSprite() != null) {
					Image newImage = item.getSprite().getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
					JButton boton = new JButton();
					boton.setIcon(new ImageIcon(newImage));
					boton.setBorderPainted(false);
					boton.setFocusPainted(false);
					boton.setContentAreaFilled(false);
					boton.setSize(60, 60);
					inventoryPane.add(boton);
				}
			}
			pack();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 400);
		}
	}

	public class MapPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image image;
			Sprite map = selectedAdventure.getMap();
			if (map != null) {
				g.drawImage(map.getImage(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), null);
			}
		}
	}

}
