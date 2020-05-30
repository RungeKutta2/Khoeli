package models;

import java.util.ArrayList;
import java.util.List;

import enums.Types;
import enums.Directions;
import enums.Genders;
import enums.Numbers;
import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggereable;

public class Location implements Observable, Triggereable {
	private String name;
	private Genders gender;
	private Numbers number;
	private String description;
	private List<Place> places;
	private List<NonPlayable> npcs;
	private List<Connection> connections;
	private List<Trigger> triggers;

	public Location(String name, Genders gender, Numbers number, String description, List<Place> places,
			List<String> npcs, List<Trigger> triggers) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.description = description;
		this.places = places;
		this.npcs = setNonPlayable(npcs);
		this.triggers = triggers;
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

	public List<NonPlayable> getNpcs() {
		return npcs;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	@Override
	public String executeTrigger(Types type, String thing) {
		Trigger foundTrigger = null;
		int i = 0;
		if(triggers != null) {
			while (foundTrigger == null && i < triggers.size()) {
				if (triggers.get(i).getType().equals(type) && triggers.get(i).getThing().equals(thing)) {
					foundTrigger = triggers.get(i);
				}
				i++;
			}
		}
		String result = "";
		if(foundTrigger != null) {
			result=foundTrigger.getOnTrigger();
			foundTrigger.executeAfterTriggers();
		}
		return result;
	}

	@Override
	public void changeDescription(String thing) {
		description = thing;
	}

//	public void addToPlace(String placeName, String item) {
//		Place place = getPlace(placeName);
//		if (place != null) {
//			place.getItems().add(item);
//		}
//		
//	}

}
