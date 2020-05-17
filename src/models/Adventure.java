package models;

public class Adventure {
	public Settings getSettings() {
		return settings;
	}

	private Settings settings;
	private Location[] locations;
	private NonPlayable[] npcs;
	private Item[] items;
	private Endgame[] endGames;

	public void customizeCharacter(String name, Genders gender) {
		settings.customizeCharacter(name, gender);
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

	public void setNpcs(NonPlayable[] npcs) {
		this.npcs = npcs;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public void setEndgames(Endgame[] endgames) {
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
		while (foundEndGame == null && i < endGames.length) {
			if (endGames[i].getAction().equals(action) && endGames[i].getCondition().equals(condition)
					&& endGames[i].getThing().equals(thing)) {
				foundEndGame = endGames[i].getDescription();
			}
			i++;
		}
		return foundEndGame;
	}

}
