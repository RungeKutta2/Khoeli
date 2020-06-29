package models.parser;

import enums.TriggerAction;
import models.Adventure;
import models.NonPlayable;
import models.Playable;

public class ParserTalkTo extends Parser {

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		Playable player = selectedAdventure.getSelectedPlayer();
		String resultado;
		TriggerAction action = request.getAction();

		if (action == TriggerAction.TALK_TO) {
			NonPlayable npc = player.getCurrentLocation().findNpc(request.getCallerObject());
			if (npc == null) {
				resultado = "No puedo hablar con " + request.getCallerObject() + ".";
			} else {
				resultado = player.talkTo(npc);
			}		}
		else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
