package models;

public interface Triggereable {
	Trigger findTrigger(Types type, String thing);

	void changeDescription(String thing);

	String getName();
}
