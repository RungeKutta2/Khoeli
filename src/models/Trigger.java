package models;

import java.util.List;

import enums.TriggerType;
import models.aftertrigger.*;

public class Trigger {
	private TriggerType type;
	private String thing;
	private String onTrigger;
	private List<AfterTriggerRequest> afterTriggers;
	AfterTrigger afterTrigger;

	public Trigger(TriggerType type, String thing, String onTrigger, List<AfterTriggerRequest> afterTriggers) {
		this.type = type;
		this.thing = thing;
		this.onTrigger = onTrigger;
		this.afterTriggers = afterTriggers;
		afterTrigger = new Add();
		afterTrigger.linkWith(new ChangeDescription()).linkWith(new Endgame()).linkWith(new Remove()).linkWith(new ChangeHP());
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
			for (AfterTriggerRequest request : afterTriggers) {
				afterTrigger.execute(request);
			}
		}
		return onTrigger;
	}

}
