package models;

public class Trigger {
	private Types type;
	private String thing;
	private String onTrigger;
	private AfterTrigger afterTrigger;

	public Trigger(Types type, String thing, String onTrigger, AfterTrigger afterTrigger) {
		this.type = type;
		this.thing = thing;
		this.onTrigger = onTrigger;
		this.afterTrigger = afterTrigger;
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

	public AfterTrigger getAfterTrigger() {
		return afterTrigger;
	}

}
