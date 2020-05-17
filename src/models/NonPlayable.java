package models;

public class NonPlayable extends Subject {
	private String id;
	private String description;
	private Numbers number;
	private String talk;
	private Trigger[] triggers;

	public NonPlayable(String id, String name, String description, Genders gender, Trigger[] triggers, String talk) {
		super(name, gender);
		this.description = description;
		this.id = id;
		this.triggers = triggers;
		this.talk = talk;
	}

	public String getDescription() {
		return description;
	}

	public String getTalk() {
		return talk;
	}

}
