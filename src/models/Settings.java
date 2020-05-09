package models;


public class Settings {
	private String welcome;
	private Playable character;

	public Settings() {
	}

	public Settings(String welcome, Playable character) {
		this.welcome = welcome;
		this.character = character;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public Playable getCharacter() {
		return character;
	}

	public void setCharacter(Playable character) {
		this.character = character;
	}
}
