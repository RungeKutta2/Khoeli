package models;

import java.util.List;

public class Location {
	private String name;

	public Location(String name, Genders gender, Numbers number, String description, List<Place> places, String[] npcs,
			Connection[] connections) {
		super();
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.description = description;
		this.places = places;
		this.npcs = npcs;
		this.connections = connections;
	}

	private Genders gender;
	private Numbers number;
	private String description;
	private List<Place> places;
	private String[] npcs;
	private Connection[] connections;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String findLocationName(Directions direction) {
		String found = null;
		int i = 0;
		while (found == null && i < connections.length) {
			if (connections[i].getDirection().equals(direction)) {
				found = connections[i].getLocation();
			}
			i++;
		}
		return found;
	}

	public String takeItem(String item, String place) {
		Place placeToSearch = places.stream().filter(x -> x.getName().equals(place)).findFirst().get();
		return placeToSearch.takeItem(item);
	}
}
