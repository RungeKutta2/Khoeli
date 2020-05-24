package models;

import java.util.List;

public class Item implements Triggereable, Observable, Obstacle {

	private String id;
	private String name;
	private Genders gender;
	private Numbers number;
	private String description;
	private List<Trigger> triggers;

	public Item(String id, String name, Genders gender, Numbers number, String description,
			List<Trigger> triggers) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.description = description;
		this.triggers = triggers;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	@Override
	public Trigger findTrigger(Types type, String thing) {
		Trigger foundTrigger = null;
		int i = 0;
		if(triggers != null) {
			while (foundTrigger == null && i < triggers.size()) {
				if (triggers.get(i).getType().equals(type) && triggers.get(i).getThing().equals(thing)) {
					foundTrigger = triggers.get(i);
				}
				i++;
			}
		}
		return foundTrigger;
	}

	@Override
	public void changeDescription(String thing) {
		description = thing;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String lookAt() {
		return description;
	}

	@Override
	public String getObstacleDescription() {
		return description;
	}

//	public String replace(String inputParsed) {
//		return inputParsed.replace(name, id);
//	}

}
