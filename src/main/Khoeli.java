package main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Endgame;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Settings;

public class Khoeli {
	private Settings settings;
	private Location[] locations;
	private NonPlayable[] npcs;
	private Item[] items;
	private Endgame[] endgames;

	public static void main(String[] args) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println("HOLA");
			Khoeli koeli = objectMapper.readValue(new File("test.json"), Khoeli.class);

			System.out.println("HOLA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Location[] getLocations() {
		return locations;
	}

	public void setLocations(Location[] locations) {
		this.locations = locations;
	}

	public NonPlayable[] getNpcs() {
		return npcs;
	}

	public void setNpcs(NonPlayable[] npcs) {
		this.npcs = npcs;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public Endgame[] getEndgames() {
		return endgames;
	}

	public void setEndgames(Endgame[] endgames) {
		this.endgames = endgames;
	}
}
