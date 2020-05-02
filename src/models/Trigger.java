package models;

public class Trigger {
	private Types type;
	private Item thing;
	private String on_trigger;
	private String after_trigger;

	public Trigger() {
	}

	public Trigger(Types type, Item thing) {
		this.type = type;
		this.thing = thing;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public Item getThing() {
		return thing;
	}

	public void setThing(Item thing) {
		this.thing = thing;
	}

	public String getOn_trigger() {
		return on_trigger;
	}

	public void setOn_trigger(String on_trigger) {
		this.on_trigger = on_trigger;
	}

	public String getAfter_trigger() {
		return after_trigger;
	}

	public void setAfter_trigger(String after_trigger) {
		this.after_trigger = after_trigger;
	}
}
