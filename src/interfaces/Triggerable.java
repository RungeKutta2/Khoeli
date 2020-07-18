package interfaces;

import enums.TriggerType;

public interface Triggerable {
	String executeTrigger(TriggerType type, String thing);

	void changeDescription(String thing);

}
