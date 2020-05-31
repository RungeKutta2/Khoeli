package models;

import enums.TriggerAction;

public class Command {
	private TriggerAction action;
	private String callerObject;
	private String receiverObject;
	
	

	public Command(String action, String callerObject, String receiverObject) {
		this.action = TriggerAction.getEnum(action);
		this.callerObject = callerObject;
		this.receiverObject = receiverObject;
	}
	
	public Command() {
		
	}
	
	public TriggerAction getAction() {
		return action;
	}

	public String getCallerObject() {
		return callerObject;
	}

	public String getReceiverObject() {
		return receiverObject;
	}

}
