package models;

import java.util.ArrayList;

public class Item {
	private String id;
	private String name;
	private Genders gender;
	private Numbers number;
	private ArrayList<String> actions;
	private ArrayList<String> effectsOver;
	private String description;
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

	public Trigger perform(Actions action) {
		Trigger t = null;
		if (actions.contains(action.toString())) {
			if (effectsOver.contains(EffectsOver.SELF)) {
				t = findTrigger(Types.ACTION, action.toString());

			}

		}
		return t;
	}

	private Trigger findTrigger(Types type, String thing) {
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
