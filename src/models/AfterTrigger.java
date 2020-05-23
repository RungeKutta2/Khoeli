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

	public void setAction(TriggerActions action) {
		this.action = action;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getActionDestination() {
		return actionDestination;
	}

	public void setActionDestination(String actionDestination) {
		this.actionDestination = actionDestination;
	}

	public String getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
}
