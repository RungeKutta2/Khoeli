package models.parser;

import enums.Directions;
import enums.TriggerAction;
import models.Adventure;
import models.Location;
import models.Playable;

public class ParserMove extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		TriggerAction action = request.getAction();

		if (action == TriggerAction.MOVE) {
			Directions direction = Directions.getDirection(request.getCallerObject());
			if (direction != null) {
				resultado = player.move(direction);
			} else {
				Location location = selectedAdventure.findLocation(request.getCallerObject());
				if (location != null) {
					resultado = player.move(location);
				} else {
					resultado = "La direccion " + request.getCallerObject() + " no existe";
				}
			}
		} else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
