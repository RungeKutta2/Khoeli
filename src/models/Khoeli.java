package models;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.Directions;
import enums.TriggerAction;
import helpers.AdventureDeserializer;
import interfaces.Observable;
import interfaces.Triggereable;

public class Khoeli {
	private Adventure selectedAdventure;

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

		selectorArchivos.setCurrentDirectory(new File("./")); // poner carpeta aventuras
		int resultado = selectorArchivos.showOpenDialog(null);
		File archivo = selectorArchivos.getSelectedFile();

		try {
			khoeli.setSelectedAdventure(archivo.getPath());
		} catch (IOException e) {
			System.err.println("archivo de aventura invalido");
		}

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\r\n");
		
		// hay que mostrar un mensaje inicial
		while (!khoeli.selectedAdventure.isEnded()) {

			Command comando = khoeli.parse(scanner.next());
			String result = khoeli.execute(comando);
			System.out.println(result);
			/*
			 * leer entrada parsear entrada ejecutar comando mostrar resultado
			 */
		}
		scanner.close();
	}

	private Command parse(String next) {
		String inputParsed = next.replaceAll("\\s+", " ").trim();
		inputParsed = replaceId(inputParsed);
		inputParsed = removeConectors(inputParsed);
		//ver tema sinonimos
		String[] parsed = inputParsed.split(" ");

		return new Command(parsed[0],parsed[1],parsed[2]);
	}
	
	public String removeConectors(String name) {
		String conectors = " (el|la|de|en) ";
		String actual = name;
		boolean distinto=true;
		do {
			actual = name.replaceFirst(conectors, " ");
			if(actual == name) {
				distinto=false;
			}
			else {
				name = actual;
			}
		}while(distinto);
		
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
		/* HACER LO DE IDS PLACES Y LOCATIONS */
//		for (Location location : selectedAdventure.getLocations()) {
//			replaced = replaced.replaceAll(location.getName(), location.getId());
//		}
//		for (Place place : selectedAdventure.getSelectedPlayer().getCurrentLocation().getPlaces(place)) {
//			replaced=replaced.replaceAll(location.getName(), location.getId());
//		}
		
		/* ACA IRIA LO DE SINONIMO DE ACCIONES */
		return replaced;
	}
	
	private String execute(Command comando) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		TriggerAction action = comando.getAction();
		
		if (action == TriggerAction.MOVE) {
			Directions direction = Directions.getDirection(comando.getCallerObject());
			if(direction == null) {
				resultado = "La direccion "+comando.getCallerObject()+" no existe";
			}
			else {
				resultado = player.move(direction);
			}

		} else if (action == TriggerAction.PICK_UP) {
			Item item = selectedAdventure.findItem(comando.getCallerObject());
			Place place = player.getCurrentLocation().getPlace(comando.getReceiverObject());
			if(item == null) {
				resultado = "No existe el item "+comando.getCallerObject();
			}
			else if(place == null) {
				resultado = "No existe el place "+comando.getReceiverObject();
			}
			else {
				resultado = player.pickUp(item, place);
			}
			
		} else if (action == TriggerAction.TALK_TO) {
			NonPlayable npc = selectedAdventure.findNpc(comando.getCallerObject());
			if(npc == null) {
				resultado = "No existe "+comando.getCallerObject();
			}
			else {
				resultado = player.talkTo(npc);
			}
			
		} else if (action == TriggerAction.USE) {
			if (comando.getReceiverObject() == null) {
				Item item = selectedAdventure.findItem(comando.getCallerObject());
				if(item == null) {
					resultado = "No existe el item "+comando.getCallerObject();
				}
				else {
					resultado = player.use(item);
				}
				
			} else {
				Item item = selectedAdventure.findItem(comando.getCallerObject());
				Triggereable affected = findTriggereable(comando.getReceiverObject());
				if(item == null) {
					resultado = "No existe el item "+comando.getCallerObject();
				}
				else if(affected == null) {
					resultado = "No existe "+comando.getCallerObject();
				}
				else {
					resultado = player.use(item, affected);
				}
				
			}
		} else if (action == TriggerAction.LOOK_AT) {
			Observable observable = findObservable(comando.getCallerObject());
			if(observable == null) {
				resultado = "No existe "+comando.getCallerObject();
			}
			else {
				resultado = player.lookAt(observable);
			}
			
		} else {
			resultado = "La acción no es correcta. Intentalo de nuevo";
		}

		return resultado;
	}
	
	public Triggereable findTriggereable(String id) {
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
		Place place = selectedAdventure.getSelectedPlayer().getCurrentLocation().getPlace(id);
		if(place != null) {
			return place;
		}
		return null;
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
