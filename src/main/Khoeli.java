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
import models.Types;

public class Khoeli implements Executable {
	private Adventure selectedAdventure;

	public Adventure getSelectedAdventure() {
		return selectedAdventure;
	}

	public void setSelectedAdventure(Adventure selectedAdventure) {
		this.selectedAdventure = selectedAdventure;
		this.currentLocation = selectedAdventure.getLocations().get(0);
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
		String obstacle = currentLocation.findObstacle(direction);
		if (obstacle == null) {
			String locationName = currentLocation.findLocationName(direction);
			Location foundLocation = selectedAdventure.findLocation(locationName);
			if (foundLocation != null) {
				currentLocation = foundLocation;
				String endGame = selectedAdventure.findEndGame("move", "location", locationName);
				if (endGame == null) {
					return currentLocation.getDescription();
				} else {
					return endGame;
				}
			} else {
				return "No hay nada al " + direction.toString();
			}
		}
		else {
			return selectedAdventure.findNpc(obstacle).getDescription();
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

	// faltar todos los usar y triggers, endgame
	@Override
	public String use(Item item) {
		String response = null;
		Trigger found = item.findTrigger(Types.ACTION, Actions.USE.toString());
		if (found != null) {
			response = found.getOn_trigger();
			String[] afterTrigger = found.getAfter_trigger().split(";");
			switch (afterTrigger[0]) {
			case "delete":
				selectedAdventure.getSettings().getCharacter().removeItem(afterTrigger[1]);
				break;
			case "add":
				selectedAdventure.getSettings().getCharacter().addItem(afterTrigger[1]);
				break;
			case "changeDescription":
				item.setDescription(afterTrigger[1]);
				break;
			}
		} else {
			response = "No se puede usar " + item.getName();
		}
		return response;
	}

	@Override
	public String use(Item itemUsado, Item itemAfectado) {
		String response = null;
		Trigger found = itemAfectado.findTrigger(Types.ITEM, itemUsado.getId());
		if (found != null) {
			response = found.getOn_trigger();
			String[] afterTrigger = found.getAfter_trigger().split(";");
			switch (afterTrigger[0]) {
			case "delete":
				selectedAdventure.getSettings().getCharacter().removeItem(afterTrigger[1]);
				break;
			case "add":
				selectedAdventure.getSettings().getCharacter().addItem(afterTrigger[1]);
				break;
			case "changeDescription":
				itemAfectado.setDescription(afterTrigger[1]);
				break;
			}
		} else {
			response = "No se puede usar " + itemUsado.getName() + " con " + itemAfectado.getName();
		}
		return response;
	}

	@Override
	public String use(Item item, NonPlayable npc) {
		String response = null;
		Trigger found = npc.findTrigger(Types.ITEM, item.getId());
		if (found != null) {
			response = found.getOn_trigger();
			String[] afterTrigger = found.getAfter_trigger().split(";");
			switch (afterTrigger[0]) {
			case "delete":
				selectedAdventure.getSettings().getCharacter().removeItem(afterTrigger[1]);
				break;
			case "add":
				selectedAdventure.getSettings().getCharacter().addItem(afterTrigger[1]);
				break;
			case "changeDescription":
				npc.setDescription(afterTrigger[1]);
				break;
			}
		} else {
			response = "No se puede usar " + item.getName() + " con " + npc.getName();
		}
		return response;
	}

	@Override
	public String use(Item item, Place place) {
		return null;
	}
}
