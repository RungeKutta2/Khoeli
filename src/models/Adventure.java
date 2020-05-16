package models;

public class Adventure {
	private Settings settings;
	private Location[] locations;
	private NonPlayable[] npcs;
	private Item[] items;
	private Endgame[] endgames;	
	
	public void configMainCharacter(String name,Genders gender) {
		settings.getCharacter().setName(name);
		settings.getCharacter().setGender(gender);
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

	public Location findLocation(String locationName) {
		for (Location location : locations) {
			if(location.getName().equals(locationName)) {
				return location;
			}
		}
		return null;
	}
	
	
	
}
