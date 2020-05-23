package models;

import java.util.List;

public class Location implements Observable {
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
		Connection connection = getConnection(direction);
		if (connection != null) {
			found = connection.getLocation();
		}
		return found;
	}

	public Connection getConnection(Directions direction) {
		Connection found = null;
		int i = 0;
		while (found == null && i < connections.size()) {
			if (connections.get(i).getDirection().equals(direction)) {
				found = connections.get(i);
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
		Connection connection = getConnection(direction);
		if (connection != null) {
			found = connection.getObstacle();
		}
		return found;
	}

	@Override
	public String lookAt() {
		return description;
	}

	public void removeObstacle(Directions direction) {
		Connection connection = getConnection(direction);
		if (connection != null) {
			connection.removeObstacle();
		}
	}

	public void removeFromPlace(String placeName, String item) {
		Place place = getPlace(placeName);
		if (place != null) {
			place.getItems().remove(item);
		}
	}

	public Place getPlace(String place) {
		Place found = null;
		int i = 0;
		while (found == null && i < places.size()) {
			if (places.get(i).getName().equals(place)) {
				found = places.get(i);
			}
			i++;
		}
		return found;
	}

	public void addToPlace(String placeName, String item) {
		Place place = getPlace(placeName);
		if (place != null) {
			place.getItems().add(item);
		}
		
	}
	
}
