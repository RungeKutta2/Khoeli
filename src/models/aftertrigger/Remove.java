package models.aftertrigger;

import enums.AfterTriggerAction;
import enums.Direction;
import models.Adventure;
import models.Connection;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Place;

public class Remove extends AfterTrigger {

	@Override
	public void execute(AfterTriggerRequest request) {
		if (request.getAction().equals(AfterTriggerAction.REMOVE)) {
			switch (request.getDestinationType()) {
			case INVENTORY:
				Item item = Adventure.getSelectedAdventure().findItem(request.getThing());
				Adventure.getSelectedAdventure().getSelectedPlayer().getInventory().remove(item);
				break;
			case CONNECTION:
				Location location1 = Adventure.getSelectedAdventure().findLocation(request.getParentId());
				Connection connection = location1.findConnection(Direction.valueOf(request.getActionDestination()));
				connection.removeObstacle();
				break;
			case PLACE:
				Location location2 = Adventure.getSelectedAdventure().findLocation(request.getParentId());
				Place place = location2.getPlace(request.getActionDestination());
				Item item2 = Adventure.getSelectedAdventure().findItem(request.getThing());
				place.getItems().remove(item2);
				break;
			case LOCATION:
				NonPlayable npc = Adventure.getSelectedAdventure().findNpc(request.getThing());
				Location location = Adventure.getSelectedAdventure().findLocation(request.getActionDestination());
				location.getNpcs().remove(npc);
				break;
			default:
				break;
			}
		} else {
			checkNext(request);
		}
	}

}
