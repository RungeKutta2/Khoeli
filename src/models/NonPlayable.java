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

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTalk() {
		return talk;
	}
	
	public Trigger findTrigger(Types type, String thing) {
		Trigger foundTrigger = null;
		int i = 0;
		while (foundTrigger == null && i < triggers.length) {
			if (triggers[i].getType().equals(type) && triggers[i].getThing().equals(thing)) {
				foundTrigger = triggers[i];
			}
			i++;
		}
		return foundTrigger;
	}

}
