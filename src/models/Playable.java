package models;

import java.util.LinkedList;
import java.util.List;

public class Playable extends Subject {
	private int healthPoints;
	private List<String> items;

	public Playable(String name, Genders gender) {
		super(name, gender);
		healthPoints = 100;
		items = new LinkedList<String>();
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	public void customize(String name, Genders gender) {
		this.name=name;
		this.gender=gender;
		
	}
	
	public void addItem(String item) {
		items.add(item);
	}

	public void removeItem(String string) {
		items.removeIf(x->x.equals(string));		
	}

}
