package main;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Action;
import models.Adventure;
import models.Endgame;
import models.Genders;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Settings;

public class Khoeli {
	private Adventure aventuraSeleccionada;
	private Location currentLocation;
	
	public static void main(String[] args) {
		
		try {
			Khoeli khoeli = new Khoeli();
			JFileChooser selectorArchivos = new JFileChooser();
			selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			selectorArchivos.setCurrentDirectory(new File("./")); //poner carpeta aventuras
			int resultado = selectorArchivos.showOpenDialog(null);
			File archivo = selectorArchivos.getSelectedFile(); 
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			khoeli.aventuraSeleccionada = objectMapper.readValue(archivo, Adventure.class);
			khoeli.aventuraSeleccionada.configMainCharacter("Camila",Genders.FEMALE);
			
			System.out.println("HOLA");
			khoeli.parse("               usar        qe       barreta   aaa mandale mecha aa a pirata fantasma a  g g   espejo          .");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String parse(String input) {
		String inputParsed = input.replaceAll("\\s+"," ").trim();	
		
		for (Action action : aventuraSeleccionada.getActions()) {
			inputParsed = action.replace(inputParsed);
		}
		
		for (NonPlayable npc : aventuraSeleccionada.getNpcs()) {
			inputParsed = npc.replace(inputParsed);
		}
		
		for (Item item : aventuraSeleccionada.getItems()) {
			inputParsed = item.replace(inputParsed);
		}
		
		
		return inputParsed;
	}
}
