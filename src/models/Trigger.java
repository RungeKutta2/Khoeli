package models;

public class Trigger {
	private Types type;
	private String thing;
	private String on_trigger;
	public String getOn_trigger() {
		return on_trigger;
	}

	private String after_trigger;

	public String getAfter_trigger() {
		return after_trigger;
	}

	public Trigger(Types type, String thing, String on_trigger, String after_trigger) {
		this.type = type;
		this.thing = thing;
		this.on_trigger = on_trigger;
		this.after_trigger = after_trigger;
	}

	public Types getType() {
		return type;
	}

	public String getThing() {
		return thing;
	}

}
