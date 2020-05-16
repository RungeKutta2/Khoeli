package models;

public class NonPlayable extends Subject implements Obstacle {
	private String id;
	private String description;
	private Numbers number;
	private String talk;
	private Trigger[] triggers;

	public NonPlayable() {
	}
	
	public NonPlayable(String name) {
		super(name);
	}

	public NonPlayable(String id,String name, Genders gender) {
		super(name, gender);
		this.id=id;
	}

	public void clearPath() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Numbers getNumber() {
		return number;
	}

	public void setNumber(Numbers number) {
		this.number = number;
	}

	public String getTalk() {
		return talk;
	}

	public void setTalk(String talk) {
		this.talk = talk;
	}

	public Trigger[] getTriggers() {
		return triggers;
	}

	public void setTriggers(Trigger[] triggers) {
		this.triggers = triggers;
	}

	public String replace(String inputParsed) {
		return inputParsed.replace(name, id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
