package interfaces;

import enums.Types;

public interface Triggereable {
	String executeTrigger(Types type, String thing);

	void changeDescription(String thing);

	String getName();
}
