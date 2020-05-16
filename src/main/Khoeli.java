package main;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Action;
import models.Adventure;
import models.Connection;
import models.Endgame;
import models.Executable;
import models.Directions;
import models.Genders;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Settings;

public class Khoeli implements Executable {
	private Adventure selectedAdventure;

	public Adventure getSelectedAdventure() {
		return selectedAdventure;
	}

	public void setSelectedAdventure(Adventure selectedAdventure) {
		this.selectedAdventure = selectedAdventure;
		this.currentLocation = selectedAdventure.getLocations()[0];
	}

	private Location currentLocation;

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public static void main(String[] args) {

		try {
			Khoeli khoeli = new Khoeli();
			JFileChooser selectorArchivos = new JFileChooser();
			selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

			selectorArchivos.setCurrentDirectory(new File("./")); // poner carpeta aventuras
			int resultado = selectorArchivos.showOpenDialog(null);
			File archivo = selectorArchivos.getSelectedFile();

			ObjectMapper objectMapper = new ObjectMapper();

			khoeli.selectedAdventure = objectMapper.readValue(archivo, Adventure.class);
			khoeli.selectedAdventure.configMainCharacter("Camila", Genders.FEMALE);

			System.out.println("HOLA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public String parse(String input) {
//		String inputParsed = input.replaceAll("\\s+", " ").trim();
//
//		for (Action action : selectedAdventure.getActions()) {
//			inputParsed = action.replace(inputParsed);
//		}
//
//		for (NonPlayable npc : selectedAdventure.getNpcs()) {
//			inputParsed = npc.replace(inputParsed);
//		}
//
//		for (Item item : selectedAdventure.getItems()) {
//			inputParsed = item.replace(inputParsed);
//		}
//
//		return inputParsed;
//	}

	public String move(Directions direction) {
		String locationName = currentLocation.findLocationName(direction);
		Location foundLocation = selectedAdventure.findLocation(locationName);
		if (foundLocation != null) {
			currentLocation = foundLocation;
			return currentLocation.getDescription();
		} else {
			return "No hay nada al " + direction.toString();
		}
	}

	public String pickUp(String item, String place) {
		return "Juntaste " +currentLocation.takeItem(item, place);
	}

	public String use(String item) {
		// TODO Auto-generated method stub
		return null;
	}

	public String lookAt(String item) {
		// TODO Auto-generated method stub
		return null;
	}
}
