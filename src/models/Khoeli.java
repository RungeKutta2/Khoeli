package models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.AttributedCharacterIterator;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import org.apache.commons.text.WordUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.Action;
import helpers.AdventureDeserializer;
import models.parser.*;

public class Khoeli {
	private Adventure selectedAdventure;
	private Parser parser;
	private BufferedImage background;
	
	public Khoeli() {
		parser = new LookAt();
		parser.linkWith(new Move())
			  .linkWith(new Pickup())
			  .linkWith(new Save())
			  .linkWith(new TalkTo())
			  .linkWith(new Use());
	}

	public Adventure getSelectedAdventure() {
		return selectedAdventure;
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

	public static void main(String[] args) {
		Khoeli khoeli = new Khoeli();
		
		File path = new File( "./partidas guardadas/");
		if (!path.exists()) {
			path.mkdirs();
		}
		
		khoeli.play();
	}

	private void play() {
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

		
		

		try {
			background = ImageIO.read(new File("./sprites/cabaña.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DrawPanel dp = new DrawPanel();
		ventana.add(dp);
		
		
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(1000, 600);
		
//		BufferedImage mesa;
//		try {
//			mesa = ImageIO.read(new File("./sprites/mesa.jpg"));
//			dp.getGraphics().drawImage(mesa, 900, 0, null);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(
				"Bienvenido a Khoeli!\r\nPuedes usar los siguientes comandos:\r\n-IR\r\n-MIRAR\r\n-HABLAR\r\n-USAR\r\n");
		System.out.println("Ingrese su nombre (si no ingresa nada, el nombre será "
				+ selectedAdventure.getSelectedPlayer().getName() + "):");

		String name = scanner.nextLine();
		if (!name.isEmpty()) {
			selectedAdventure.getSelectedPlayer().setName(name);
		}
		
		System.out.println(selectedAdventure.getWelcomeMessage());
		System.out.println();
		System.out.println(selectedAdventure.getSelectedPlayer().getCurrentLocation().getDescription());

		while (!selectedAdventure.isEnded()) {
			String entrada = scanner.nextLine();
			Command comando = Parser.parse(entrada);

			if (comando != null) {
				selectedAdventure.appendRequest(entrada);
				String result = parser.execute(selectedAdventure, comando);
				selectedAdventure.appendResponse(result);
				final char[] var = { '.', '\n' };
				System.out.println(WordUtils.capitalizeFully(result, var));
			}
		}
		
		
		if(selectedAdventure.getSelectedPlayer().getHP() <= 0) {
			System.out.println("Te moriste. Mejor suerte la proxima ...");
		}else {
			System.out.println("Felicidades! has llegado al final. Te esperamos en tu proxima aventura.");
		}
		
		int dialogResult = JOptionPane.showConfirmDialog(null, "\r\n¿Desea guardar su recorrido en la aventura?\r\n","Khoeli",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date(System.currentTimeMillis());
		if(dialogResult == JOptionPane.YES_OPTION){
		  parser.execute(selectedAdventure, new Command(Action.SAVE.toString(),selectedAdventure.getSelectedPlayer().getName()+" - " +formatter.format(date) ,null));
		}	
		
		scanner.close();
	}
	
	private class DrawPanel extends JPanel {
		private static final long serialVersionUID = 91574813372177663L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			BufferedImage mesa;
			Dimension currentDimension = new Dimension(800,600);
			g2.scale(currentDimension.getWidth() / 800, currentDimension.getHeight() / 450);

			g2.drawImage(background, null, 0, 0);
			try {
				mesa = ImageIO.read(new File("./sprites/mesa.jpg"));
				g2.drawImage(mesa, 900, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(800, 450);
		}
	}

}
