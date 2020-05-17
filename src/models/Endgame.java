package models;

public class Endgame {
	private String condition;
	private String action;
	public String getCondition() {
		return condition;
	}

	public String getAction() {
		return action;
	}

	public String getThing() {
		return thing;
	}

	public String getDescription() {
		return description;
	}

	private String thing;
	private String description;

	public Endgame(String condition, String action, String thing, String description) {
		this.condition = condition;
		this.action = action;
		this.thing = thing;
		this.description = description;
	}

}
