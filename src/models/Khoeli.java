package models;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.text.WordUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import helpers.AdventureDeserializer;
import models.parser.*;

public class Khoeli {
	private Adventure selectedAdventure;
	private Parser parser;

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
		khoeli.play();
	}

	private void play() {
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

		System.out.println(
				"Bienvenido a Khoeli!\r\nPuedes usar los siguientes comandos:\r\n-IR\r\n-MIRAR\r\n-HABLAR\r\n-USAR\r\n");
		System.out.println("Ingrese su nombre (si no ingresa nada, el nombre será "
				+ selectedAdventure.getSelectedPlayer().getName() + "):");

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\r\n");
		String name = scanner.next();
		if (!name.isEmpty()) {
			selectedAdventure.getSelectedPlayer().setName(name);
		}

		System.out.println(selectedAdventure.getWelcomeMessage());
		System.out.println();
		System.out.println(selectedAdventure.getSelectedPlayer().getCurrentLocation().getDescription());

		while (!selectedAdventure.isEnded()) {
			String entrada = scanner.next();
			Command comando = Parser.parse(entrada);

			if (comando != null) {
				selectedAdventure.appendRequest(entrada);
				String result = parser.execute(selectedAdventure, comando);
				selectedAdventure.appendResponse(result);
				final char[] var = { '.', '\n' };
				System.out.println(WordUtils.capitalizeFully(result, var));
			}

		}
		scanner.close();
	}


}
