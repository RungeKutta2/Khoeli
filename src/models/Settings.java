package models;

public class Settings {
	private String welcome;
	private Playable character;

	public Settings(String welcome, Playable character) {
		this.welcome = welcome;
		this.character = character;
	}
	
	public Playable getCharacter() {
		return character;
	}

	public void customizeCharacter(String name, Genders gender) {
		character.customize(name,gender);
	}
}
