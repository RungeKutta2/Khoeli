package models.aftertrigger;

import enums.AfterTriggerAction;
import enums.AfterTriggerDestination;
import enums.AfterTriggerThing;

public class AfterTriggerRequest {
	private AfterTriggerAction action;
	private String thing;
	private String actionDestination;
	private AfterTriggerDestination destinationType;
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

	public AfterTriggerDestination getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(AfterTriggerDestination destinationType) {
		this.destinationType = destinationType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public AfterTriggerRequest(AfterTriggerAction action, String thing, AfterTriggerThing thingType, String parentId, String actionDestination,
			AfterTriggerDestination destinationType) {
		this.action = action;
		this.thing = thing;
		this.actionDestination = actionDestination;
		this.destinationType = destinationType;
		this.parentId = parentId;
	}
}
