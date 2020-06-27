package models;

import java.util.ArrayList;
import java.util.List;

import enums.Types;
import enums.Directions;
import enums.Genders;
import enums.Numbers;
import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggerable;

public class Location implements Observable, Triggerable {
	private String id;
	private String name;
	private Genders gender;
	private Numbers number;
	private String description;
	private List<Place> places;
	private List<NonPlayable> npcs;
	private List<Connection> connections;
	private List<Trigger> triggers;

	public Location(String id, String name, Genders gender, Numbers number, String description, List<Place> places,
			List<String> npcs, List<Trigger> triggers) {
		this.id = id;
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Place> getPlaces() {
		return places;
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
	
	public Connection findConnection(Location location) {
		Connection found = null;
		int i = 0;
		while (found == null && i < connections.size()) {
			if (connections.get(i).getLocation().equals(location)) {
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
		if (triggers != null) {
			while (foundTrigger == null && i < triggers.size()) {
				if (triggers.get(i).getType().equals(type) && triggers.get(i).getThing().equals(thing)) {
					foundTrigger = triggers.get(i);
				}
				i++;
			}
		}
		String result = "";
		if (foundTrigger != null) {
			result = foundTrigger.getOnTrigger();
			foundTrigger.executeAfterTriggers();
		}
		return result;
	}

	@Override
	public void changeDescription(String thing) {
		description = thing;
	}

	public boolean contains(Item item) {
		boolean found = false;
		int i = 0;
		while (!found && i < places.size()) {
			if (places.get(i).getItems().contains(item)) {
				found = true;
			}
			i++;
		}
		if (!found) {
			i = 0;
			while (!found && i < connections.size()) {
				Obstacle obstacle = connections.get(i).getObstacle();
				if (obstacle != null && obstacle.equals(item)) {
					found = true;
				}
				i++;
			}
		}
		return found;
	}

	public boolean contains(NonPlayable npc) {

		boolean found = npcs.contains(npc);

		if (!found) {
			int i = 0;
			while (!found && i < connections.size()) {
				Obstacle obstacle = connections.get(i).getObstacle();
				if (obstacle != null && obstacle.equals(npc)) {
					found = true;
				}
				i++;
			}
		}
		return found;
	}

	public boolean contains(Place place) {
		return places.contains(place);
	}

	public Triggerable findTriggerable(String id) {

		Item item = Adventure.getSelectedAdventure().findItem(id);
		if (item != null && contains(item)) {
			return item;
		}

		NonPlayable npc = findNpc(id);
		if (npc != null) {
			return npc;
		}

		Place place = getPlace(id);
		if (place != null && contains(place)) {
			return place;
		}

		return null;
	}

	public NonPlayable findNpc(String id) {

		NonPlayable npc = Adventure.getSelectedAdventure().findNpc(id);
		if (npc != null && contains(npc)) {
			return npc;
		}
		
		return null;
	}

//	public void addToPlace(String placeName, String item) {
//		Place place = getPlace(placeName);
//		if (place != null) {
//			place.getItems().add(item);
//		}
//		
//	}

}
