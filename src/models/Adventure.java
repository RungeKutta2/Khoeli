package models;

import java.util.ArrayList;
import java.util.List;

public class Adventure {

	private Settings settings;
	private List<Location> locations;
	private List<NonPlayable> npcs;
	private List<Item> items;
	private List<Endgame> endGames;

	public Adventure() {
		locations = new ArrayList<Location>();
		npcs = new ArrayList<NonPlayable>();
		items = new ArrayList<Item>();
		endGames = new ArrayList<Endgame>();
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void setNpcs(List<NonPlayable> npcs) {
		this.npcs = npcs;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setEndgames(List<Endgame> endgames) {
		this.endGames = endgames;
	}

	public void customizeCharacter(String name, Genders gender) {
		settings.customizeCharacter(name, gender);
	}

	public Location findLocation(String locationName) {
		for (Location location : locations) {
			if (location.getName().equals(locationName)) {
				return location;
			}
		}
		return null;
	}

	public String findEndGame(String action, String condition, String thing) {
		String foundEndGame = null;
		int i = 0;
		while (foundEndGame == null && i < endGames.size()) {
			if (endGames.get(i).getAction().equals(action) && endGames.get(i).getCondition().equals(condition)
					&& endGames.get(i).getThing().equals(thing)) {
				foundEndGame = endGames.get(i).getDescription();
			}
			i++;
		}
		return foundEndGame;
	}

	public Obstacle findObstacle(String id) {
		for (NonPlayable npc : npcs) {
			if (npc.getName().equals(id)) {
				return npc;
			}
		}
		for (Item item : items) {
			if(item.getId().equals(id)) {
				return item;
			}
			
		}
		return null;
	}

}
