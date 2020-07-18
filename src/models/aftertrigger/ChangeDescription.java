package models.aftertrigger;

import enums.AfterTriggerAction;
import models.Adventure;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Place;

public class ChangeDescription extends AfterTrigger {

	@Override
	public void execute(AfterTriggerRequest request) {
		if(request.getAction().equals(AfterTriggerAction.CHANGE_DESCRIPTION)) {
			switch (request.getDestinationType()) {
			case ITEM:
				Item affectedItem = Adventure.getSelectedAdventure().findItem(request.getActionDestination());
				affectedItem.changeDescription(request.getThing());
				break;
			case NPC:
				NonPlayable affectedNpc = Adventure.getSelectedAdventure().findNpc(request.getActionDestination());
				affectedNpc.changeDescription(request.getThing());
				break;
			case LOCATION:
				Location affectedLocation = Adventure.getSelectedAdventure()
						.findLocation(request.getActionDestination());
				affectedLocation.changeDescription(request.getThing());
				break;
			case PLACE:
				Location location2 = Adventure.getSelectedAdventure().findLocation(request.getParentId());
				Place place = location2.getPlace(request.getActionDestination());
				place.changeDescription(request.getThing());
				break;
			default:
				break;
			}
		}else {
			checkNext(request);
		}
		
	}

}
