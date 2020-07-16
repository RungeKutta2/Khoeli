package models.aftertrigger;

import enums.AfterTriggerAction;
import models.Adventure;

public class ChangeHP extends AfterTrigger{

	@Override
	public void execute(AfterTriggerRequest request) {
		
		if(request.getAction().equals(AfterTriggerAction.CHANGE_HP)) {
			int hp = Integer.parseInt(request.getThing());
			Adventure.getSelectedAdventure().getSelectedPlayer().changeHP(hp);
			if(Adventure.getSelectedAdventure().getSelectedPlayer().getHP() <= 0) {
				Adventure.getSelectedAdventure().end();
			}
		}else {
			checkNext(request);
		}
		
	}

}
