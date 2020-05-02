package models;

public class Endgame {
	private String condition;
	private String action;
	private String thing;
	private String description;

	public Endgame() {
	}

	public Endgame(String condition, String action, String thing, String description) {
		this.condition = condition;
		this.action = action;
		this.thing = thing;
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
