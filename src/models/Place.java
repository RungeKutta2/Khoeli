package models;

import java.util.List;

public class Place {
	private String name;
	private Genders gender;
	private Numbers number;
	private List<String> items;

	public Place() {
	}

	public Place(String name, Genders gender, Numbers number, List<String> items) {
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genders getGender() {
		return gender;
	}

	public void setGender(Genders gender) {
		this.gender = gender;
	}

	public Numbers getNumber() {
		return number;
	}

	public void setNumber(Numbers number) {
		this.number = number;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

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

}
