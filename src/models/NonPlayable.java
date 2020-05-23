package models;

import java.util.List;

public class NonPlayable implements Triggereable, Observable{
	private String id;
	private String description;
	private Numbers number;
	private String talk;
	private List<Trigger> triggers;
	private String name;
	private Genders gender;

	public NonPlayable(String id, String name, String description, Genders gender, List<Trigger> triggers, String talk) {
		this.name = name;
		this.gender = gender;
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
	
	public String getNameNpc() {
		return name;
	}	
	
	public Genders getGender() {
		return gender;
	}

	public void setGender(Genders gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Trigger findTrigger(Types type, String thing) {
		Trigger foundTrigger = null;
		int i = 0;
		while (foundTrigger == null && i < triggers.size()) {
			if (triggers.get(i).getType().equals(type) && triggers.get(i).getThing().equals(thing)) {
				foundTrigger = triggers.get(i);
			}
			i++;
		}
		return foundTrigger;
	}

	@Override
	public void changeDescription(String thing) {
		description = thing;
	}
	
	@Override
	public String getName() {
		return id;
	}

	@Override
	public String lookAt() {
		return description;
	}
}
