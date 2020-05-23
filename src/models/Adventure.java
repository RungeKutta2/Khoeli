package models;

import java.util.ArrayList;
import java.util.List;

public class Adventure {

	private Settings settings;
	private List<Location> locations;
	private List<NonPlayable> npcs;
	private List<Item> items;
	private List<Endgame> endGames;

	public Settings getSettings() {
		return settings;
	}

	public void customizeCharacter(String name, Genders gender) {
		settings.customizeCharacter(name, gender);
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public void setNpcs(ArrayList<NonPlayable> npcs) {
		this.npcs = npcs;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public void setEndgames(ArrayList<Endgame> endgames) {
		this.endGames = endgames;
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
	
	public NonPlayable findNpc(String npcId) {
		for (NonPlayable npc : npcs) {
			if (npc.getName().equals(npcId)) {
				return npc;
			}
		}
		return null;
	}
	

}
