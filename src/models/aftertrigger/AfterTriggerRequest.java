package models.aftertrigger;

import enums.AfterTriggerAction;
import enums.DestinationTypes;
import enums.ThingType;

public class AfterTriggerRequest {
	private AfterTriggerAction action;
	private String thing;
	private String actionDestination;
	private DestinationTypes destinationType;
	private String parentId;
	
	public AfterTriggerAction getAction() {
		return action;
	}

	public void setAction(AfterTriggerAction action) {
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

	public DestinationTypes getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(DestinationTypes destinationType) {
		this.destinationType = destinationType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public AfterTriggerRequest(AfterTriggerAction action, String thing, ThingType thingType, String parentId, String actionDestination,
			DestinationTypes destinationType) {
		this.action = action;
		this.thing = thing;
		this.actionDestination = actionDestination;
		this.destinationType = destinationType;
		this.parentId = parentId;
	}
}
