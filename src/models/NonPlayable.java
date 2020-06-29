package models;

import java.util.List;
import java.util.Optional;

import enums.TriggerType;
import enums.Gender;
import enums.Number;
import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggerable;

public class NonPlayable implements Triggerable, Observable, Obstacle {
	private String id;
	private String name;
	private String description;
//	private Genders gender;
//	private Numbers number;
	private String talk;
	private List<Trigger> triggers;

	public NonPlayable(String id, String name, String description, Gender gender, Number number, List<Trigger> triggers,
			String talk) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.triggers = triggers;
		this.talk = talk;
//		this.number = number;
//		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public String getTalk() {
		return talk;
	}

	@Override
	public String executeTrigger(TriggerType type, String thing) {
		Trigger foundTrigger = findTrigger(type, thing);
		String result = "";
		if (foundTrigger != null) {
			result = foundTrigger.execute();
		}
		return result;
	}

	private Trigger findTrigger(TriggerType type, String thing) {
		Trigger found = null;
		if (type != null && thing != null) {
			Optional<Trigger> result = triggers.stream()
					.filter(x -> x.getType().equals(type) && x.getThing().equals(thing)).findFirst();
			found = result.isPresent() ? result.get() : null;
		}
		return found;
	}

	public String getId() {
		return id;
	}

	public String getName2() {
		return name;
	}

	@Override
	public void changeDescription(String thing) {
		description = thing;
	}

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
