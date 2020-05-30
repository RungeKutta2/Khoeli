package models;

import java.util.List;

import enums.Types;

public class Trigger {
	private Types type;
	private String thing;
	private String onTrigger;
	private List<AfterTrigger> afterTriggers;

	public Trigger(Types type, String thing, String onTrigger, List<AfterTrigger> afterTriggers) {
		this.type = type;
		this.thing = thing;
		this.onTrigger = onTrigger;
		this.afterTriggers = afterTriggers;
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

	public List<AfterTrigger> getAfterTriggers() {
		return afterTriggers;
	}

	public void executeAfterTriggers() {
		if (afterTriggers != null) {
			for (AfterTrigger afterTrigger : afterTriggers) {
				afterTrigger.execute();
			}
		}
	}

}
