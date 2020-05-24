package models;

import java.util.LinkedList;
import java.util.List;

public class Playable implements Executable {
	private int healthPoints;
	private List<Item> items;
	private String name;
	private Genders gender;
	private Location currentLocation;

	public String getName() {
		return name;
	}

	public Genders getGender() {
		return gender;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public Playable(String name, Genders gender, Settings settings, List<String> items) {
		this.name = name;
		this.gender = gender;
		healthPoints = settings.getInitialHealthPoints();
		currentLocation = Adventure.getSelectedAdventure().findLocation(settings.getInitialLocation());
		this.items = setItem(items);
	}

	private List<Item> setItem(List<String> items) {
		List<Item> itemList = new LinkedList<Item>();
		for (String item : items) {
			itemList.add(Adventure.getSelectedAdventure().findItem(item));
		}

		return itemList;
	}

	public List<Item> getItems() {
		return items;
	}

	public void customize(String name, Genders gender) {
		this.name = name;
		this.gender = gender;

	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	@Override
	public String move(Directions direction) {
		Connection connection = currentLocation.findConnection(direction);
		String result;
		if (connection != null) {
			if (connection.getObstacle() == null) {
				// NO ESTAMOS CHEQUEANDO EL END GAME PORQUE TENEMOS QUE HACERLO TRIGGER
				currentLocation = connection.getLocation();
				result = currentLocation.getDescription();
			} else {
				result = connection.getObstacle().getObstacleDescription();
			}
		} else {
			result = "No hay nada al " + direction.toString();
		}

		return result;
	}

	@Override
	public String pickUp(Item item, Place place) {
		String result;
		boolean itemResult = currentLocation.takeItem(item, place);
		if (itemResult) {
			result = "Juntaste " + item.getName();
		} else {
			result = "No hay " + item.getName() + " en " + place.getName();
		}
		return result;
	}

	@Override
	public String lookAt(Observable observable) {
		return observable.lookAt();
	}

	@Override
	public String talkTo(NonPlayable npc) {
		String talk = npc.getTalk();
		if (talk.isEmpty()) {
			return "No tiene nada que decir";
		}
		return talk;
	}

	@Override
	public String use(Item item) {
		String response = null;
		Trigger found = item.findTrigger(Types.ACTION, TriggerActions.USE.toString());
		if (found != null) {
			response = found.getOnTrigger();
			List<AfterTrigger> afterTriggerList = found.getAfterTrigger();
			for (AfterTrigger afterTrigger : afterTriggerList) {
				switch (afterTrigger.getAction()) {
				case REMOVE:
					// removeItem(afterTrigger.getThing());
					break;
				case CHANGE_DESCRIPTION:
					item.changeDescription(afterTrigger.getThing());
					break;
				case ADD:
					// addItem(afterTrigger.getThing());
					break;
				default:
					break;
				}
			}

		} else {
			response = "No se puede usar " + item.getName();
		}
		return response;
	}

	@Override
	public String use(Item item, Triggereable affected) {
		String response = null;
		Trigger found = affected.findTrigger(Types.ITEM, item.getId());
		if (found != null) {
			response = found.getOnTrigger();
			List<AfterTrigger> afterTriggerList = found.getAfterTrigger();
			for (AfterTrigger afterTrigger : afterTriggerList) {
				switch (afterTrigger.getAction()) {
				case REMOVE:
					// removeItem(afterTrigger.getThing());
					break;
				case CHANGE_DESCRIPTION:
					affected.changeDescription(afterTrigger.getThing());
					break;
				case ADD:
					// addItem(afterTrigger.getThing());
					break;
				default:
					break;
				}
			}
		} else {
			response = "No se puede usar " + item.getName() + " con " + affected.getName();
		}
		return response;
	}

}
