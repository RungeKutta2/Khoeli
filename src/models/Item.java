package models;

import java.util.ArrayList;

public class Item{
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;
	private Genders gender;
	private Numbers number;
	private ArrayList<String> actions;
	private ArrayList<String> effectsOver;
	private String description;
	public void setDescription(String description) {
		this.description = description;
	}

	private Trigger[] triggers;

	public String getDescription() {
		return description;
	}

	public Item(String id, String name, Genders gender, Numbers number, ArrayList<String> actions, String description,
			Trigger[] triggers) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.actions = actions;
		this.description = description;
		this.triggers = triggers;
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


//	public String replace(String inputParsed) {
//		return inputParsed.replace(name, id);
//	}

}
