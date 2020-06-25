package models;
import java.util.List;
import enums.TriggerAction;
import enums.Types;
import interfaces.Executable;
import interfaces.Observable;
import interfaces.Triggerable;
import enums.Genders;
import enums.Directions;

public class Playable implements Executable {
	private int healthPoints;
	private Inventory inventory;
	private String name;
	private Genders gender;
	private Location currentLocation;
	//current place??

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
		this.inventory = setItem(items);
	}

	private Inventory setItem(List<String> items) {
		Inventory inventory = new Inventory();
		for (String item : items) {
			inventory.add(Adventure.getSelectedAdventure().findItem(item));
		}

		return inventory;
	}

	public void customize(String name, Genders gender) {
		this.name = name;
		this.gender = gender;

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
				result += currentLocation.executeTrigger(Types.ACTION, TriggerAction.MOVE.toString());
			} else {
				result = connection.getObstacle().getObstacleDescription();
			}
		} else {
			result = "No hay nada al " + direction.getDescription();
		}

		return result;
	}

	@Override
	public String pickUp(Item item, Place place) {
		String result;
		boolean itemResult = currentLocation.takeItem(item, place);
		if (itemResult) {
			result = "Juntaste " + item.getName();
			inventory.add(item);
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
		String onTrigger = npc.executeTrigger(Types.ACTION, TriggerAction.TALK_TO.toString()); 
		if (talk.isEmpty()) {
			return "No tiene nada que decir";
		}
		else if (onTrigger != null && !onTrigger.isEmpty()) {
			talk=onTrigger;
		}
		return talk;
	}

	@Override
	public String use(Item item) {
		String response = item.executeTrigger(Types.ACTION, TriggerAction.USE.toString());
		if(response == null || response.isEmpty()) {
			response = "no se puede usar "+ item.getName();
		}
		return response;
	}

	@Override
	public String use(Item item, Triggerable affected) {
		String response = affected.executeTrigger(Types.ITEM, item.getId());
		if(response == null || response.isEmpty()) {
			response = "no se puede usar "+ item.getName() + " con " +affected.getName();
		}
		return response;
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public Item findItem(String id) {
		Item item = Adventure.getSelectedAdventure().findItem(id);
		if(inventory.contains(item) || currentLocation.contains(item)) {
			return item;
		}
		return null;
	}
	
	public Observable findObservable(String id) {
		
		Item item = findItem(id);
		if(item != null) {
			return item;
		}
		
		NonPlayable npc = Adventure.getSelectedAdventure().findNpc(id);
		if(npc != null && currentLocation.getNpcs().contains(npc)) {
			return npc;
		}
		
		Place place = currentLocation.getPlace(id);
		if(place != null) {
			return place;
		}
		
		if(currentLocation.getId().equals(id)) {
			return currentLocation;
		}
		
		return null;
	}

}
