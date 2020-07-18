package models;

import java.util.List;
import java.util.Optional;

import enums.TriggerType;
import enums.Gender;
import enums.Number;
import interfaces.Observable;
import interfaces.Triggerable;

public class Place implements Triggerable, Observable {
	private String name;
	private Gender gender;
	private Number number;
	private Inventory items;
	private String description;
	private List<Trigger> triggers;
	private Sprite sprite;

	
	public Place(String name, Gender gender, Number number, List<String> items, String description, Sprite sprite) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.items = setItems(items);
		this.description = description;
		this.items.setEmptyInventoryDescription(
				"No hay nada útil en " + getDefinedArticle(this.gender, this.number) + " " + name);
		this.items.setFullInventoryDescription(
				"En " + getDefinedArticle(this.gender, this.number) + " " + name + " hay:\n");
		this.sprite = sprite;
	}

	private Inventory setItems(List<String> items) {
		Inventory inventory = new Inventory();
		for (String itemId : items) {
			inventory.add(Adventure.getSelectedAdventure().findItem(itemId));
		}

		return inventory;
	}

	public Sprite getSprite() {
		return sprite;
	}

	
	public String getName() {
		return name;
	}

	public Inventory getItems() {
		return items;
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

	public Item findItem(String id) {
		Item item = Adventure.getSelectedAdventure().findItem(id);
		if (item != null && items.contains(item)) {
			return item;
		}

		return null;

	}
	@Override
	public void changeDescription(String thing) {
		description = thing;
	}

	@Override
	public String lookAt() {
		String inventoryDescription = description + System.lineSeparator()
				+ (items.isEmpty() ? items.getEmptyInventoryDefaultDescription()
						: (items.getFullInventoryDefaultDescription() + items.lookAt()));

		return inventoryDescription;
	}

}
