package models;

import java.util.List;

import enums.Types;
import models.aftertrigger.*;

public class Trigger {
	private Types type;
	private String thing;
	private String onTrigger;
	private List<AfterTriggerRequest> afterTriggers;
	AfterTrigger middleware;
	public Trigger(Types type, String thing, String onTrigger, List<AfterTriggerRequest> afterTriggers) {
		this.type = type;
		this.thing = thing;
		this.onTrigger = onTrigger;
		this.afterTriggers = afterTriggers;
		middleware = new AfterTriggerAdd();
		middleware.linkWith(new AfterTriggerChangeDescription())
				.linkWith(new AfterTriggerEndgame())
				.linkWith(new AfterTriggerRemove());
	}

	public Types getType() {
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

	public void executeAfterTriggers() {
		if (afterTriggers != null) {
			for (AfterTriggerRequest afterTrigger : afterTriggers) {
				middleware.execute(afterTrigger);
			}
		}
	}

}
