package models.parser;

import enums.Action;
import interfaces.Triggerable;
import models.Adventure;
import models.Item;
import models.Playable;

public class Use extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		Action action = request.getAction();

		if (action == Action.USE) {
			Item item = player.findItem(request.getCallerObject());
			if (request.getReceiverObject() == null) {

				if (item == null) {
					resultado = "No existe el item " + request.getCallerObject();
				} else {
					resultado = player.use(item);
				}
			} else {
				Triggerable affected = player.findTriggerable(request.getReceiverObject());
				if (item == null) {
					resultado = "No existe el item " + request.getCallerObject();
				} else if (affected == null) {
					resultado = "No existe " + request.getReceiverObject();
				} else {
					resultado = player.use(item, affected);
				}

			}
		} else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
