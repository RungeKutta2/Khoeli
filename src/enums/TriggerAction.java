package enums;

public enum TriggerAction{
	MOVE,
	PICK_UP,
	USE,
	LOOK_AT,
	TALK_TO;
	
	public static TriggerAction getEnum(String action) {
		try {
			return valueOf(action);
		} catch (Exception e) {
			return null;
		}
	}
}
