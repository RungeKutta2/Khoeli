package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggerable;

public class Adventure {

	private List<Location> locations;
	private List<NonPlayable> npcs;
	private List<Item> items;
	private List<Endgame> endGames;
	private Playable selectedPlayer;
	private boolean ended;
	private static Adventure selectedAdventure = null;

	public static Adventure init(List<Location> locations, List<NonPlayable> npcs, List<Item> items,
			List<Endgame> endGames, Playable selectedPlayer) {
		if (selectedAdventure != null) {
			throw new AssertionError("You already initialized me");
		}
		selectedAdventure = new Adventure(locations, npcs, items, endGames, selectedPlayer);
		
		return selectedAdventure;
	}

	public static Adventure getSelectedAdventure() {
		if (selectedAdventure == null) {
			selectedAdventure = new Adventure();
		}
		return selectedAdventure;
	}

	private Adventure(List<Location> locations, List<NonPlayable> npcs, List<Item> items, List<Endgame> endGames,
			Playable selectedPlayer) {
		this.locations = locations;
		this.npcs = npcs;
		this.items = items;
		this.endGames = endGames;
		this.selectedPlayer = selectedPlayer;
		ended = false;
	}

	private Adventure() {

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

	public List<Endgame> getEndGames() {
		return endGames;
	}

	public void setEndGames(List<Endgame> endGames) {
		this.endGames = endGames;
	}

	public Playable getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Playable selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	public Location findLocation(String id) {
		int i = 0;
		Location foundLocation = null;

		while (foundLocation == null && i < locations.size()) {
			if (locations.get(i).getId().equals(id)) {
				foundLocation = locations.get(i);
			}
			i++;
		}

		return foundLocation;
	}

	public Item findItem(String itemId) {
		int i = 0;
		Item foundItem = null;

		while (foundItem == null && i < items.size()) {
			if (items.get(i).getId().equals(itemId)) {
				foundItem = items.get(i);
			}
			i++;
		}

		return foundItem;
	}
	
//	public Triggerable findTriggerable(String triggerableId) {
//		int i = 0;
//		Item foundTriggerable = null;
//
//		while (foundTriggerable == null && i < items.size()) {
//			if (items.get(i).getId().equals(triggerableId)) {
//				foundTriggerable = items.get(i);
//			}
//			if (npcs.get(i).getId().equals(triggerableId)) {
//				foundTriggerable = items.get(i);
//			}
//			if (locations.get(i).getId().equals(triggerableId)) {
//				foundTriggerable = items.get(i);
//			}
//			if () {
//				foundTriggerable = items.get(i);
//			}
//			i++;
//		}
//
//		return foundTriggerable;
//	}

	
	
	public NonPlayable findNpc(String npcId) {
		int i = 0;
		NonPlayable foundNpc = null;

		while (foundNpc == null && i < npcs.size()) {
			if (npcs.get(i).getId().equals(npcId)) {
				foundNpc = npcs.get(i);
			}
			i++;
		}

		return foundNpc;
	}

	// HAY QUE SACAR ESTO PORQUE DEBERIAMOS HACERLO COMO UN TRIGGER
	public String findEndGame(String action, String condition, String thing) {
		String foundEndGame = null;
		int i = 0;
		while (foundEndGame == null && i < endGames.size()) {
			if (endGames.get(i).getAction().equals(action) && endGames.get(i).getCondition().equals(condition)
					&& endGames.get(i).getThing().equals(thing)) {
				foundEndGame = endGames.get(i).getDescription();
			}
			i++;
		}
		return foundEndGame;
	}

	public Obstacle findObstacle(String id) {
		if (id != null) {
			for (NonPlayable npc : npcs) {
				if (npc.getName().equals(id)) {
					return npc;
				}
			}
			for (Item item : items) {
				if (item.getId().equals(id)) {
					return item;
				}

			}
		}
		return null;
	}

	
	public void executeAfterTriggers(List<AfterTrigger> afterTriggers) {

	}

	public void end() {
		ended = true;
	}

}
