package models;

public class Settings {
	private String welcome;
	private String initialLocation;
	private int initialHealthPoints;
	
	public Settings(String welcome, String initialLocation, int initialHealthPoints) {
		this.welcome = welcome;
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
