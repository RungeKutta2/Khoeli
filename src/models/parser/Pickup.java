package models.parser;

import enums.Action;
import models.Adventure;
import models.Item;
import models.Place;
import models.Playable;

public class Pickup extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		Action action = request.getAction();

		if (action == Action.PICK_UP) {
			Place place;
			if (request.getReceiverObject() == null) {
				place = player.getCurrentPlace();
			} else {
				place = player.getCurrentLocation().getPlace(request.getReceiverObject());
			}
			if (place != null) {
				Item item = place.findItem(request.getCallerObject());
				if (item != null) {
					resultado = player.pickUp(item, place);
				} else {
					resultado = "No encuentro ese item.";
				}
			} else {
				resultado = "No se de dónde agarrarlo.";
			}		}
		else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
