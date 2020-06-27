package models;

import enums.AfterTriggerAction;
import enums.DestinationTypes;
import enums.Directions;
import interfaces.Obstacle;

public class AfterTrigger {
	private AfterTriggerAction action;
	private String thing;
	private String actionDestination;
	private DestinationTypes destinationType;
	private String parentId;
	
	public AfterTrigger(AfterTriggerAction action, String thing, ThingType thingType, String parentId, String actionDestination,
			DestinationTypes destinationType) {
		this.action = action;
		this.thing = thing;
		this.actionDestination = actionDestination;
		this.destinationType = destinationType;
		this.parentId = parentId;
	}

	public AfterTriggerAction getAction() {
		return action;
	}

	public String getThing() {
		return thing;
	}

	public String getActionDestination() {
		return actionDestination;
	}

	public DestinationTypes getDestinationType() {
		return destinationType;
	}

	public void execute() {
		switch (action) {
		case ADD:
			executeAdd();
			break;
		case REMOVE:
			executeRemove();
			break;
		case ENDGAME:
			executeEndgame();
			break;
		case CHANGE_DESCRIPTION:
			executeChangeDescription();
			break;
		}
	}

	private void executeChangeDescription() {
		switch (destinationType) {
		case ITEM:
			Item affectedItem = Adventure.getSelectedAdventure().findItem(actionDestination);
			affectedItem.changeDescription(thing);
			break;
		case NPC:
			NonPlayable affectedNpc = Adventure.getSelectedAdventure().findNpc(actionDestination);
			affectedNpc.changeDescription(thing);
			break;
		case LOCATION:
			Location affectedLocation = Adventure.getSelectedAdventure().findLocation(actionDestination);
			affectedLocation.changeDescription(thing);
			break;
		default:
			break;
		}
	}

	private void executeEndgame() {
		Adventure.getSelectedAdventure().end();
	}

	private void executeRemove() {
		switch (destinationType) {
		case INVENTORY:
			Item item = Adventure.getSelectedAdventure().findItem(thing);
			Adventure.getSelectedAdventure().getSelectedPlayer().getInventory().remove(item);
			break;
		case CONNECTION:
			Location location1 = Adventure.getSelectedAdventure().findLocation(parentId);
			Connection connection = location1.findConnection(Directions.valueOf(actionDestination));
			connection.removeObstacle();
			break;
		case PLACE:
			Location location2 = Adventure.getSelectedAdventure().findLocation(parentId);
			Place place = location2.getPlace(actionDestination);
			Item item2 = Adventure.getSelectedAdventure().findItem(thing);
			place.getItems().remove(item2);
			break;
		case LOCATION:
			NonPlayable npc = Adventure.getSelectedAdventure().findNpc(thing);
			Location location = Adventure.getSelectedAdventure().findLocation(actionDestination);
			location.getNpcs().remove(npc);
			break;
		default:
			break;
		}
	}

	private void executeAdd() {
		switch (destinationType) {
		case INVENTORY:
			Item item = Adventure.getSelectedAdventure().findItem(thing);
			Adventure.getSelectedAdventure().getSelectedPlayer().getInventory().add(item);
			break;
		case CONNECTION:
			Location location1 = Adventure.getSelectedAdventure().findLocation(parentId);
			Connection connection = location1.findConnection(Directions.valueOf(actionDestination));
			Obstacle obstacle = Adventure.getSelectedAdventure().findObstacle(thing);
			
			connection.setObstacle(obstacle);
			break;
		case PLACE:
			Location location2 = Adventure.getSelectedAdventure().findLocation(parentId);
			Place place = location2.getPlace(actionDestination);
			Item item2 = Adventure.getSelectedAdventure().findItem(thing);
			place.getItems().add(item2);
			break;
		case LOCATION:
			NonPlayable npc = Adventure.getSelectedAdventure().findNpc(thing);
			Location location = Adventure.getSelectedAdventure().findLocation(actionDestination);
			location.getNpcs().add(npc);
		default:
			break;
		}
	}

}
