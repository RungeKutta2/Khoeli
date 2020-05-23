package models;

public class AfterTrigger {
	private TriggerActions action;
	private String thing;
	private String actionDestination;
	private String destinationType;

	public AfterTrigger(TriggerActions action, String thing, String actionDestination, String destinationType) {
		this.action = action;
		this.thing = thing;
		this.actionDestination = actionDestination;
		this.destinationType = destinationType;
	}

	public TriggerActions getAction() {
		return action;
	}

	public String getThing() {
		return thing;
	}

	public String getActionDestination() {
		return actionDestination;
	}

	public String getDestinationType() {
		return destinationType;
	}

}
