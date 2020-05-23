package models;

import java.util.List;

public class Location {
	private String name;
	private Genders gender;
	private Numbers number;
	private String description;
	private List<Place> places;
	private List<String> npcs;
	private List<Connection> connections;

	public Location(String name, Genders gender, Numbers number, String description, List<Place> places,
			List<String> npcs, List<Connection> connections) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.description = description;
		this.places = places;
		this.npcs = npcs;
		this.connections = connections;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String findLocationName(Directions direction) {
		String found = null;
		int i = 0;
		while (found == null && i < connections.size()) {
			if (connections.get(i).getDirection().equals(direction)) {
				found = connections.get(i).getLocation();
			}
			i++;
		}
		return found;
	}

	public String takeItem(String item, String place) {
		Place placeToSearch = places.stream().filter(x -> x.getName().equals(place)).findFirst().get();
		return placeToSearch.takeItem(item);
	}

	public String findObstacle(Directions direction) {
		String found = null;
		int i = 0;
		while (found == null && i < connections.size()) {
			if (connections.get(i).getDirection().equals(direction)) {
				found = connections.get(i).getObstacle();
			}
			i++;
		}
		return found;
	}
}
