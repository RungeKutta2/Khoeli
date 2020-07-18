package models;

import java.util.List;
import enums.Action;
import enums.TriggerType;
import interfaces.Observable;
import interfaces.Triggerable;
import enums.Gender;
import enums.Direction;

public class Playable implements Observable {
	private int healthPoints;
	private Inventory inventory;
	private String name;
	private Gender gender;
	private Location currentLocation;
	private Place currentPlace;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public Place getCurrentPlace() {
		return currentPlace;
	}

	public Playable(String name, Gender gender, Settings settings, List<String> items) {
		this.name = name;
		this.gender = gender;
		healthPoints = settings.getInitialHealthPoints();
		currentLocation = Adventure.getSelectedAdventure().findLocation(settings.getInitialLocation());
		this.inventory = setItems(items);
		this.inventory.setEmptyInventoryDescription("No hay nada en el inventario");
		this.inventory.setFullInventoryDescription("En mi inventario hay:\n");
	}

	private Inventory setItems(List<String> items) {
		Inventory inventory = new Inventory();
		for (String item : items) {
			inventory.add(Adventure.getSelectedAdventure().findItem(item));
		}

		return inventory;
	}

	public void customize(String name, Gender gender) {
		this.name = name;
		this.gender = gender;

	}

	public int getHP() {
		return healthPoints;
	}
	
	public void changeHP(int hp) {
		healthPoints=Math.min(100, healthPoints+hp);
	}
	
	public String move(Direction direction) {
		Connection connection = currentLocation.findConnection(direction);
		String result;
		if (connection != null) {
			if (connection.getObstacle() == null) {
				currentLocation = connection.getLocation();
				result = currentLocation.getDescription();
				String triggerResult = currentLocation.executeTrigger(TriggerType.ACTION, Action.MOVE.toString());
				if (!triggerResult.isEmpty()) {
					result += System.lineSeparator() + triggerResult;
				}
			} else {
				result = connection.getObstacle().getObstacleDescription();
			}
		} else {
			result = "No hay nada al " + direction.getDescription();
		}

		return result;
	}

	public String move(Location location) {
		Connection connection = currentLocation.findConnection(location);
		String result;
		if (connection != null) {
			if (connection.getObstacle() == null) {
				currentLocation = connection.getLocation();
				result = currentLocation.getDescription();
				String triggerResult = currentLocation.executeTrigger(TriggerType.ACTION, Action.MOVE.toString());
				if (!triggerResult.isEmpty()) {
					result += System.lineSeparator() + triggerResult;
				}
			} else {
				result = connection.getObstacle().getObstacleDescription();
			}
		} else {
			result = "No hay " + location.getName();
		}

		return result;
	}

	public String pickUp(Item item, Place place) {

		String response = item.executeTrigger(TriggerType.ACTION, Action.PICK_UP.toString());
		if (response == null || response.isEmpty()) {
			return response = "No puedo juntar eso.";
		}

		return response;
	}

	public String lookAt(Observable observable) {
		if (observable instanceof Place) {
			currentPlace = (Place) observable;
		}
		return observable.lookAt();
	}

	public String talkTo(NonPlayable npc) {
		String talk = npc.getTalk();
		String onTrigger = npc.executeTrigger(TriggerType.ACTION, Action.TALK_TO.toString());
		if (talk.isEmpty()) {
			return "No tiene nada que decir";
		} else if (onTrigger != null && !onTrigger.isEmpty()) {
			talk = onTrigger;
		}
		return talk;
	}

	public String use(Item item) {
		String response = item.executeTrigger(TriggerType.ACTION, Action.USE.toString());
		if (response == null || response.isEmpty()) {
			response = "no se puede usar " + item.getName();
		}
		return response;
	}

	public String use(Item item, Triggerable affected) {
		String response = affected.executeTrigger(TriggerType.ITEM, item.getId());
		if (response == null || response.isEmpty()) {
			response = "No puedo hacer eso";
		}
		return response;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Item findItem(String id) {
		Item item = Adventure.getSelectedAdventure().findItem(id);
		if (item != null && (inventory.contains(item) || currentLocation.contains(item))) {
			return item;
		}
		return null;
	}

	public Observable findObservable(String id) {

		if (id.equals("inventario")) {
			return this;
		}

		Item item = findItem(id);
		if (item != null) {
			return item;
		}

		NonPlayable npc = Adventure.getSelectedAdventure().findNpc(id);
		if (npc != null && currentLocation.getNpcs().contains(npc)) {
			return npc;
		}

		Place place = currentLocation.getPlace(id);
		if (place != null) {
			return place;
		}

		if (currentLocation != null && currentLocation.getId().equals(id)) {
			return currentLocation;
		}

		return null;
	}

	public Triggerable findTriggerable(String receiverObject) {
		Item item = Adventure.getSelectedAdventure().findItem(receiverObject);
		if (inventory.contains(item)) {
			return item;
		} else {
			return currentLocation.findTriggerable(receiverObject);
		}
	}

	@Override
	public String lookAt() {
		String inventoryDescription = inventory.isEmpty() ? inventory.getEmptyInventoryDefaultDescription()
				: (inventory.getFullInventoryDefaultDescription() + inventory.lookAt());

		return inventoryDescription;
	}

}
