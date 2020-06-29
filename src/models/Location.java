package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import enums.TriggerType;
import enums.Direction;
import enums.Gender;
import enums.Number;
import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggerable;

public class Location implements Observable, Triggerable{
	private String id;
	private String name;
//	private Genders gender;
//	private Numbers number;
	private String description;
	private List<Place> places;
	private List<NonPlayable> npcs;
	private List<Connection> connections;
	private List<Trigger> triggers;

	public Location(String id, String name, Gender gender, Number number, String description, List<Place> places,
			List<String> npcs, List<Trigger> triggers) {
		this.id = id;
		this.name = name;
//		this.gender = gender;
//		this.number = number;
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

	public Location findLocationName(Direction direction) {
		Location found = null;
		Connection connection = findConnection(direction);
		if (connection != null) {
			found = connection.getLocation();
		}
		return found;
	}

	public Connection findConnection(Direction direction) {
		Connection found = null;
		if (direction != null) {
			Optional<Connection> result = connections.stream().filter(x -> x.getDirection().equals(direction)).findFirst();
			found = result.orElseGet(null);
		}
		return found;
	}
	
	public Connection findConnection(Location location) {
		Connection found = null;
		if (location != null) {
			Optional<Connection> result = connections.stream().filter(x -> x.getLocation().equals(location)).findFirst();
			found = result.orElseGet(null);
		}
		return found;	}

	public Obstacle findObstacle(Direction direction) {
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

	public void removeObstacle(Direction direction) {
		Connection connection = findConnection(direction);
		if (connection != null) {
			connection.removeObstacle();
		}
	}

	public Place getPlace(String place) {
		Place found = null;
		if (place != null) {
			Optional<Place> result = places.stream().filter(x -> x.getName().equals(place)).findFirst();
			found = result.orElseGet(null);
		}
		return found;
	}

	public List<NonPlayable> getNpcs() {
		return npcs;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	@Override
	public String executeTrigger(TriggerType type, String thing) {
		Trigger foundTrigger = findTrigger(type, thing);
		String result = "";
		if (foundTrigger != null) {
			result = foundTrigger.execute();
		}
		return result;
	}

	private Trigger findTrigger(TriggerType type, String thing) {
		Trigger found = null;
		if (type != null && thing != null) {
			Optional<Trigger> result = triggers.stream().filter(x -> x.getType().equals(type) && x.getThing().equals(thing)).findFirst();
			found = result.orElseGet(null);
		}
		return found;
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
}
