package models;

public class Location {
	private String name;
	private Genders gender;
	private Numbers number;
	private String description;
	private Place[] places;
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

	public Place[] getPlaces() {
		return places;
	}

	public void setPlaces(Place[] places) {
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
}
