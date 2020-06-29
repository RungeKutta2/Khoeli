package models;

import java.util.List;

import enums.TriggerType;
import models.aftertrigger.*;

public class Trigger {
	private TriggerType type;
	private String thing;
	private String onTrigger;
	private List<AfterTriggerRequest> afterTriggers;
	AfterTrigger middleware;

	public Trigger(TriggerType type, String thing, String onTrigger, List<AfterTriggerRequest> afterTriggers) {
		this.type = type;
		this.thing = thing;
		this.onTrigger = onTrigger;
		this.afterTriggers = afterTriggers;
		middleware = new Add();
		middleware.linkWith(new ChangeDescription()).linkWith(new Endgame()).linkWith(new Remove());
	}

	public TriggerType getType() {
		return type;
	}

	public String getThing() {
		return thing;
	}

	public String getOnTrigger() {
		return onTrigger;
	}

	public List<AfterTriggerRequest> getAfterTriggers() {
		return afterTriggers;
	}

	public String execute() {
		if (afterTriggers != null) {
			for (AfterTriggerRequest afterTrigger : afterTriggers) {
				middleware.execute(afterTrigger);
			}
		}
		return onTrigger;
	}

}
