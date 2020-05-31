package models;

import java.util.ArrayList;
import java.util.List;

import enums.Types;
import enums.Genders;
import enums.Numbers;
import interfaces.Triggereable;

public class Place implements Triggereable{ //agregar observable y descripcion
	private String name;
	private Genders gender;
	private Numbers number;
	private Inventory items;
	private List<Trigger> triggers;

	public Place(String name, Genders gender, Numbers number, List<String> items) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.items = setItems(items);
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

	@Override
	public void changeDescription(String thing) {		
	}

}
