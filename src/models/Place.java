package models;
import java.text.Normalizer;
import java.util.List;
import enums.Types;
import enums.Genders;
import enums.Numbers;
import interfaces.Observable;
import interfaces.Triggerable;

public class Place implements Triggerable, Observable{
	private String name;
	private Genders gender;
	private Numbers number;
	private Inventory items;
	private String description;
	private List<Trigger> triggers;

	public Place(String name, Genders gender, Numbers number, List<String> items, String description) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.items = setItems(items);
		this.description = description;
	}
	
	private Inventory setItems(List<String> items){
		Inventory inventory = new Inventory();
		for (String itemId : items) {
			inventory.add(Adventure.getSelectedAdventure().findItem(itemId));
		}
		
		return inventory;
	}
	

	public String getName() {
		return name;
	}

	public Inventory getItems() {
		return items;
	}

	@Override
	public String executeTrigger(Types type, String thing) {
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
		String result = "";
		if(foundTrigger!= null) {
			result  = foundTrigger.getOnTrigger();
			foundTrigger.executeAfterTriggers();
		}
		return result;
	}

	public Item findItem(String id) {
		Item item = Adventure.getSelectedAdventure().findItem(id);
		if (item != null && items.contains(item)) {
			return item;
		}

		return null;

	}
	
	public void changeDescription(String thing) {
		description = thing;
	}

	@Override
	public String lookAt() {
		return   description + System.lineSeparator() +"En " + getDefineArticle(gender, number) + " " + name + " hay:" + System.lineSeparator() + items.lookAt();
	}

}
