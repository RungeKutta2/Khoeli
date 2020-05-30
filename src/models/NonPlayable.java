package models;

import java.util.List;

import enums.Types;
import enums.Genders;
import enums.Numbers;
import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggereable;

public class NonPlayable implements Triggereable, Observable, Obstacle {
	private String id;
	private String name;
	private String description;
	private Genders gender;
	private Numbers number;
	private String talk;
	private List<Trigger> triggers;

	public NonPlayable(String id, String name, String description, Genders gender, Numbers number,
			List<Trigger> triggers, String talk) {
		this.name = name;
		this.gender = gender;
		this.description = description;
		this.id = id;
		this.triggers = triggers;
		this.talk = talk;
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public String getTalk() {
		return talk;
	}

	@Override
	public String executeTrigger(Types type, String thing) {
		Trigger foundTrigger = null;
		int i = 0;
		if (triggers != null) {
			while (foundTrigger == null && i < triggers.size()) {
				if (triggers.get(i).getType().equals(type) && triggers.get(i).getThing().equals(thing)) {
					foundTrigger = triggers.get(i);
				}
				i++;
			}
		}
		String result = "";
		if (foundTrigger != null) {
			result = foundTrigger.getOnTrigger();
			foundTrigger.executeAfterTriggers();
		}
		return result;
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
