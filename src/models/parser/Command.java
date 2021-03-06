package models.parser;

import enums.Action;

public class Command {
	private Action action;
	private String callerObject;
	private String receiverObject;
	
	

	public Command(String action, String callerObject, String receiverObject) {
		this.action = Action.get(action);
		this.callerObject = callerObject;
		this.receiverObject = receiverObject;
	}
	
	public Command() {
		
	}
	
	public Action getAction() {
		return action;
	}

	public String getCallerObject() {
		return callerObject;
	}

	public String getReceiverObject() {
		return receiverObject;
	}

	@Override
	public String toString() {
		return action + " " + callerObject + " " +receiverObject != null? receiverObject:"";
	}

}
