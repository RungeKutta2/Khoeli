package interfaces;

import enums.Types;

public interface Triggerable {
	String executeTrigger(Types type, String thing);

	void changeDescription(String thing);

	String getName();
}
