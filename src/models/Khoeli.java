package models;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.Directions;
import enums.TriggerAction;
import helpers.AdventureDeserializer;
import interfaces.Observable;
import interfaces.Triggerable;

public class Khoeli {
	private Adventure selectedAdventure;
	private StringBuilder history;

	public Khoeli() {
		history = new StringBuilder();
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
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

		selectorArchivos.setCurrentDirectory(new File("./aventuras/"));
		int resultado = selectorArchivos.showOpenDialog(null);
		if (selectorArchivos.getSelectedFile() == null) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame,
					"No seleccionó ninguna aventura! Ejecute de nuevo el programa y seleccione una aventura válida.",
					"Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		File archivo = selectorArchivos.getSelectedFile();

		try {
			khoeli.setSelectedAdventure(archivo.getPath());
		} catch (IOException e) {
			System.err.println("archivo de aventura invalido");
		}

		System.out.println("Bienvenido a Khoeli!\r\nPuedes usar los siguientes comandos:\r\n-IR\r\n-MIRAR\r\n-HABLAR\r\n-USAR\r\n");
		System.out.println("Ingrese su nombre (si no ingresa nada, el nombre será " + khoeli.selectedAdventure.getSelectedPlayer().getName() + "):");
		
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\r\n");
		String name = scanner.next();
		if(!name.isEmpty()) {
			khoeli.selectedAdventure.getSelectedPlayer().setName(name);
		}
		
		System.out.println(khoeli.selectedAdventure.getWelcomeMessage());
		System.out.println();
		System.out.println(khoeli.selectedAdventure.getSelectedPlayer().getCurrentLocation().getDescription());

		

		while (!khoeli.selectedAdventure.isEnded()) {
			String entrada = scanner.next();
			Command comando = khoeli.parse(entrada);

			if (comando != null) {
				khoeli.history.append(khoeli.selectedAdventure.getSelectedPlayer().getName()).append(": ").append(entrada);
				khoeli.history.append(System.lineSeparator());
				String result = khoeli.execute(comando);
				khoeli.history.append(result);
				khoeli.history.append(System.lineSeparator());
				final char[] var = { '.', '\n' };
				System.out.println(WordUtils.capitalizeFully(result, var));
			}

		}
		scanner.close();
	}

	
	private Command parse(String next) {
		String var = Normalizer.normalize(next.replaceAll("\\s+", " ").trim().toLowerCase(),Normalizer.Form.NFD).replaceAll("[\\u0300-\\u0301]", "");
		String inputParsed = Normalizer.normalize(var, Normalizer.Form.NFC);
		inputParsed = replaceId(inputParsed);
		inputParsed = removeConectors(inputParsed);
		String[] parsed = inputParsed.split(" ");

		Command comando = null;
		try {
			comando = new Command(parsed[0], parsed[1], parsed.length == 3 ? parsed[2] : null);
		} catch (Exception e) {
			System.out.println("Acción incorrecta, intente nuevamente.");
		}
		return comando;
	}

	public String removeConectors(String name) {
		String conectors = " (el|los|la|las|un|una|unos|unas|al|del|a|ante|bajo|con|contra|de|desde|durante|en|entre|hacia|hasta|mediante|para|por|segun|sin|sobre|tras) ";
		String actual = name;
		boolean distinto = true;
		do {
			actual = name.replaceFirst(conectors, " ");
			if (actual == name) {
				distinto = false;
			} else {
				name = actual;
			}
		} while (distinto);

		return name;
	}

	public String replaceId(String name) {
		String replaced = name;
		for (NonPlayable npc : selectedAdventure.getNpcs()) {
			replaced = replaced.replaceAll(npc.getName2(), npc.getId());
		}
		for (Item item : selectedAdventure.getItems()) {
			replaced = replaced.replaceAll(item.getName(), item.getId());
		}
		for (Location location : selectedAdventure.getLocations()) {
			replaced = replaced.replaceAll(location.getName(), location.getId());
		}
		return replaced;
	}

	private String execute(Command comando) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		TriggerAction action = comando.getAction();

		if (action == TriggerAction.MOVE) {
			Directions direction = Directions.getDirection(comando.getCallerObject());
			if (direction != null) {
				resultado = player.move(direction);
			} else {
				Location location = selectedAdventure.findLocation(comando.getCallerObject());
				if (location != null) {
					resultado = player.move(location);
				} else {
					resultado = "La direccion " + comando.getCallerObject() + " no existe";
				}
			}

		} else if (action == TriggerAction.PICK_UP) {
			Place place;
			if (comando.getReceiverObject() == null) {
				place = player.getCurrentPlace();
			} else {
				place = player.getCurrentLocation().getPlace(comando.getReceiverObject());
			}
			if (place != null) {
				Item item = place.findItem(comando.getCallerObject());
				if (item != null) {
					resultado = player.pickUp(item, place);
				} else {
					resultado = "No encuentro ese item.";
				}
			} else {
				resultado = "No se de dónde agarrarlo.";
			}
		} else if (action == TriggerAction.TALK_TO) {
			NonPlayable npc = player.getCurrentLocation().findNpc(comando.getCallerObject());
			if (npc == null) {
				resultado = "No puedo hablar con " + comando.getCallerObject() + ".";
			} else {
				resultado = player.talkTo(npc);
			}

		} else if (action == TriggerAction.USE) {
			Item item = player.findItem(comando.getCallerObject());
			if (comando.getReceiverObject() == null) {

				if (item == null) {
					resultado = "No existe el item " + comando.getCallerObject();
				} else {
					resultado = player.use(item);
				}
			} else {
				Triggerable affected = player.findTriggerable(comando.getReceiverObject());
				if (item == null) {
					resultado = "No existe el item " + comando.getCallerObject();
				} else if (affected == null) {
					resultado = "No existe " + comando.getReceiverObject();
				} else {
					resultado = player.use(item, affected);
				}

			}
		} else if (action == TriggerAction.LOOK_AT) {
			Observable observable = player.findObservable(comando.getCallerObject());
			if (observable == null) {
				resultado = "No existe " + comando.getCallerObject();
			} else {
				resultado = player.lookAt(observable);
			}
		} else if (action == TriggerAction.SAVE) {
			String string = comando.getCallerObject();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter("./partidas guardadas/" + string + ".txt"));
				writer.append(history);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			resultado = "Se ha guardado exitosamente.";

		} else {
			resultado = "La acción no es correcta. Intentalo de nuevo";
		}

		return resultado;
	}

	public Observable findObservable(String id) {
		/* Agregar todos los demas */
		for (NonPlayable npc : selectedAdventure.getNpcs()) {
			if (npc.getName().equals(id)) {
				return npc;
			}
		}
		for (Item item : selectedAdventure.getItems()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		for (Location location : selectedAdventure.getLocations()) {
			if (location.getName().equals(id)) {
				return location;
			}
		}
//		Place place = selectedAdventure.getSelectedPlayer().getCurrentLocation().getPlace(id);
//		if(place != null) {
//			return place;
//		}

		return null;
	}

}
