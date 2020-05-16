package models;

import java.util.List;

public class Location {
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return name.equals(other.name);
	}

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

	public Location() {
	}

	public Location(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genders getGender() {
		return gender;
	}

	public void setGender(Genders gender) {
		this.gender = gender;
	}

	public Numbers getNumber() {
		return number;
	}

	public void setNumber(Numbers number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public String[] getNpcs() {
		return npcs;
	}

	public void setNpcs(String[] npcs) {
		this.npcs = npcs;
	}

	public Connection[] getConnections() {
		return connections;
	}

	public void setConnections(Connection[] connections) {
		this.connections = connections;
	}

	public String findLocationName(Directions direction) {
		String found = null;
		int i = 0;
		while(found == null && i < connections.length) {
			if(connections[i].getDirection().equals(direction)) {
				found = connections[i].getLocation();
			}
			i++;
		}
		return found;
	}

	public String takeItem(String item, String place) {
		Place placeToSearch = places.stream().filter(x-> x.getName().equals(place)).findFirst().get();
		return placeToSearch.takeItem(item);
	}
}
