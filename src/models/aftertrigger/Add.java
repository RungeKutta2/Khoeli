package models.aftertrigger;

import enums.AfterTriggerAction;
import enums.Direction;
import interfaces.Obstacle;
import models.Adventure;
import models.Connection;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Place;

public class Add extends AfterTrigger {

	@Override
	public void execute(AfterTriggerRequest request) {
		
		if(request.getAction().equals(AfterTriggerAction.ADD)) {
			switch (request.getDestinationType()) {
			case INVENTORY:
				Item item = Adventure.getSelectedAdventure().findItem(request.getThing());
				Adventure.getSelectedAdventure().getSelectedPlayer().getInventory().add(item);
				break;
			case CONNECTION:
				Location location1 = Adventure.getSelectedAdventure().findLocation(request.getParentId());
				Connection connection = location1.findConnection(Direction.valueOf(request.getActionDestination()));
				Obstacle obstacle = Adventure.getSelectedAdventure().findObstacle(request.getThing());
				connection.setObstacle(obstacle);
				break;
			case PLACE:
				Location location2 = Adventure.getSelectedAdventure().findLocation(request.getParentId());
				Place place = location2.getPlace(request.getActionDestination());
				Item item2 = Adventure.getSelectedAdventure().findItem(request.getThing());
				place.getItems().add(item2);
				break;
			case LOCATION:
				NonPlayable npc = Adventure.getSelectedAdventure().findNpc(request.getThing());
				Location location = Adventure.getSelectedAdventure().findLocation(request.getActionDestination());
				location.getNpcs().add(npc);
			default:
				break;
			}
		}
		else {
			checkNext(request);
		}
		
	}

}
