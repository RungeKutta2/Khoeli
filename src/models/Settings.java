package models;

public class Settings {
	private String initialLocation;
	private int initialHealthPoints;
	
	public Settings(String initialLocation, int initialHealthPoints) {
		this.initialLocation = initialLocation;
		this.initialHealthPoints = initialHealthPoints;
	}

	public String getInitialLocation() {
		return initialLocation;
	}

	public int getInitialHealthPoints() {
		return initialHealthPoints;
	}
	
}
