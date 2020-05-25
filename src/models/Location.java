package models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class Location implements Observable{
	private String name;
	private Genders gender;
	private Numbers number;
	private String description;
	private List<Place> places;
	private List<NonPlayable> npcs;
	private List<Connection> connections;

	public Location(String name, Genders gender, Numbers number, String description, List<Place> places,
			List<String> npcs, List<Connection> connections) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.description = description;
		this.places = places;
//		this.npcs = setNonPlayable(npcs);
		this.connections = connections;
	}

	private List<NonPlayable> setNonPlayable(List<String> npcs) {
		List<NonPlayable> npcList = new ArrayList<NonPlayable>(npcs.size());
		for (String npcId : npcs) {
			npcList.add(Adventure.getSelectedAdventure().findNpc(npcId));
		}

		return npcList;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Location findLocationName(Directions direction) {
		Location found = null;
		Connection connection = findConnection(direction);
		if (connection != null) {
			found = connection.getLocation();
		}
		return found;
	}

	public Connection findConnection(Directions direction) {
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

	public Obstacle findObstacle(Directions direction) {
		Obstacle found = null;
		Connection connection = findConnection(direction);
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
		Connection connection = findConnection(direction);
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

	public boolean takeItem(Item item, Place place) {
		return place.getItems().remove(item);
	}

	
//	public void addToPlace(String placeName, String item) {
//		Place place = getPlace(placeName);
//		if (place != null) {
//			place.getItems().add(item);
//		}
//		
//	}

}

