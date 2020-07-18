package models.parser;

import enums.Direction;
import enums.Action;
import models.Adventure;
import models.Location;
import models.Playable;

public class Move extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		Action action = request.getAction();

		if (action == Action.MOVE) {
			Direction direction = Direction.getDirection(request.getCallerObject());
			if (direction != null) {
				resultado = player.move(direction);
			} else {
				Location location = selectedAdventure.findLocation(request.getCallerObject());
				if (location != null) {
					resultado = player.move(location);
				} else {
					resultado = "No puedo ir hacia allí";
				}
			}
		} else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
