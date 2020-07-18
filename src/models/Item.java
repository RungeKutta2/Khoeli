package models;

import java.util.List;
import java.util.Optional;

import enums.TriggerType;
import enums.Gender;
import enums.Number;
import interfaces.Observable;
import interfaces.Obstacle;
import interfaces.Triggerable;

public class Item implements Triggerable, Observable, Obstacle {

	private String id;
	private String name;
	private Gender gender;
	private Number number;
	private String description;
	private List<Trigger> triggers;
	private Sprite sprite;

	public Item(String id, String name, Gender gender, Number number, String description, List<Trigger> triggers,Sprite sprite) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.description = description;
		this.triggers = triggers;
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public Gender getGender() {
		return gender;
	}

	public Number getNumber() {
		return number;
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

	@Override
	public void changeDescription(String thing) {
		description = thing;
	}

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
