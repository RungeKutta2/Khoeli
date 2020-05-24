package models;

import java.util.List;

public class NonPlayable implements Triggereable, Observable, Obstacle {
	private String id;
	private String description;
	private Numbers number;
	private String talk;
	private List<Trigger> triggers;
	private String name;
	private Genders gender;

	public NonPlayable(String id, String name, String description, Genders gender, List<Trigger> triggers,
			String talk) {
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

	public String getTalk() {
		return talk;
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

	
	public String getId() {
		return id;
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

	@Override
	public String getObstacleDescription() {
		return talk;
	}
}
