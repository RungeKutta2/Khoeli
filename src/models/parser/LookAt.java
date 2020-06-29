package models.parser;

import enums.Action;
import interfaces.Observable;
import models.Adventure;
import models.Playable;

public class LookAt extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		Action action = request.getAction();

		if (action == Action.LOOK_AT) {
			Observable observable = player.findObservable(request.getCallerObject());
			if (observable == null) {
				resultado = "No existe " + request.getCallerObject();
			} else {
				resultado = player.lookAt(observable);
			}
		} else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}
}
