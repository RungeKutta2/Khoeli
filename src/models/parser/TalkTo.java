package models.parser;

import enums.Action;
import models.Adventure;
import models.NonPlayable;
import models.Playable;

public class TalkTo extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		Action action = request.getAction();

		if (action == Action.TALK_TO) {
			NonPlayable npc = player.getCurrentLocation().findNpc(request.getCallerObject());
			if (npc == null) {
				resultado = "No parece que tenga ganas de hablar.";
			} else {
				resultado = player.talkTo(npc);
			}		}
		else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
