package main;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Actions;
import models.Adventure;
import models.Connection;
import models.Endgame;
import models.Executable;
import models.Directions;
import models.Genders;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Place;
import models.Settings;
import models.Trigger;

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
			khoeli.selectedAdventure.customizeCharacter("Camila", Genders.FEMALE);

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
			String endGame= selectedAdventure.findEndGame("move","location",locationName);
			if(endGame==null) {
				return currentLocation.getDescription();
			}
			else {
				return endGame;
			}
		} else {
			return "No hay nada al " + direction.toString();
		}
	}
	
	public String pickUp(String item, String place) {
		String itemString = currentLocation.takeItem(item, place);
		if (itemString != null) {
			return "Juntaste " + itemString;
		}
		return "No hay " + item + " en " + place;
	}

	@Override
	public String lookAt(Item item) {
		return item.getDescription();
	}

	@Override
	public String lookAt(NonPlayable npc) {
		return npc.getDescription();
	}

	@Override
	public String lookAt(Location location) {
		return location.getDescription();
	}

	public String talkTo(NonPlayable npc) {
		String talk = npc.getTalk();
		if (talk.isEmpty()) {
			return "No tiene nada que decir";
		}
		return talk;
	}
	//faltar todos los usar y triggers, endgame 
	@Override
	public String use(Item item) {
		Trigger t = item.perform(Actions.USE);
		switch (t.getAfter_trigger()) {
		case "remove":
			break;
		
		}
		return t.getOn_trigger();
	}

	@Override
	public String use(Item itemUsado, Item itemAfectado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String use(Item item, NonPlayable npc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String use(Item item, Place place) {
		// TODO Auto-generated method stub
		return null;
	}
}
