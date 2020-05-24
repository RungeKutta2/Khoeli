package models;

import java.util.ArrayList;
import java.util.List;

public class Place {
	private String name;
	private Genders gender;
	private Numbers number;
	private List<Item> items;

	public Place(String name, Genders gender, Numbers number, List<String> items) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.items = setItems(items);
	}
	
	private List<Item> setItems(List<String> items){
		List<Item> itemList = new ArrayList<Item>(items.size());
		for (String itemId : items) {
			itemList.add(Adventure.getSelectedAdventure().findItem(itemId));
		}
		
		return itemList;
	}
	

	public String getName() {
		return name;
	}

	public List<Item> getItems() {
		return items;
	}

	/*
	public String takeItem(String item) {
		int itemPosition = 0;
		String foundItem = null;
		
		while (foundItem == null && itemPosition < items.size()) {
			if (items.get(itemPosition).equals(item)) {
				foundItem = items.get(itemPosition);
			} else {
				itemPosition++;
			}
		}
		if (foundItem != null) {
			items.remove(itemPosition);
		}	
		return foundItem;
	}
	*/
}
