package models.aftertrigger;

import enums.AfterTriggerAction;
import models.Adventure;

public class Endgame extends AfterTrigger {

	@Override
	public void execute(AfterTriggerRequest request) {
		if (request.getAction().equals(AfterTriggerAction.ENDGAME)) {
			Adventure.getSelectedAdventure().end();
		} else {
			checkNext(request);
		}
	}

}
