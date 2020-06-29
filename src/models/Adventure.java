package models;

import java.util.List;
import java.util.Optional;
import interfaces.Obstacle;

public class Adventure {

	private List<Location> locations;
	private List<NonPlayable> npcs;
	private List<Item> items;
	private Playable selectedPlayer;
	private boolean ended;
	private String welcomeMessage;
	private StringBuilder history;

	private static Adventure selectedAdventure = null;

	public static Adventure init(List<Location> locations, List<NonPlayable> npcs, List<Item> items,
			Playable selectedPlayer) {
		if (selectedAdventure != null) {
			throw new AssertionError("You already initialized me");
		}
		selectedAdventure = new Adventure(locations, npcs, items, selectedPlayer);

		return selectedAdventure;
	}

	public static Adventure getSelectedAdventure() {
		if (selectedAdventure == null) {
			selectedAdventure = new Adventure();
		}
		return selectedAdventure;
	}

	private Adventure(List<Location> locations, List<NonPlayable> npcs, List<Item> items, Playable selectedPlayer) {
		this.locations = locations;
		this.npcs = npcs;
		this.items = items;
		this.selectedPlayer = selectedPlayer;
		history = new StringBuilder();
		ended = false;
	}

	private Adventure() {
		history = new StringBuilder();
		ended = false;
	}

	public boolean isEnded() {
		return ended;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<NonPlayable> getNpcs() {
		return npcs;
	}

	public void setNpcs(List<NonPlayable> npcs) {
		this.npcs = npcs;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Playable getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Playable selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	public Location findLocation(String id) {
		Location found = null;
		if (id != null) {
			Optional<Location> result = locations.stream().filter(x -> x.getId().equals(id)).findFirst();
			found = result.isPresent() ? result.get() : null;
		}
		return found;
	}

	public Item findItem(String id) {
		Item found = null;
		if (id != null) {
			Optional<Item> result = items.stream().filter(x -> x.getId().equals(id)).findFirst();
			found = result.isPresent() ? result.get() : null;
		}
		return found;
	}

	public NonPlayable findNpc(String id) {
		NonPlayable found = null;
		if (id != null) {
			Optional<NonPlayable> result = npcs.stream().filter(x -> x.getId().equals(id)).findFirst();
			found = result.isPresent() ? result.get() : null;
		}
		return found;
	}

	public Obstacle findObstacle(String id) {
		Obstacle found = null;
		if (id != null) {
			found = findNpc(id);
			if (found == null) {
				found = findItem(id);
			}
		}
		return found;
	}

	public void end() {
		ended = true;
	}

	public void appendRequest(String request) {
		history.append(selectedPlayer.getName()).append(": ").append(request).append(System.lineSeparator());
	}

	public void appendResponse(String response) {
		history.append(response).append(System.lineSeparator());
	}

	public StringBuilder getHistory() {
		return history;
	}

}
