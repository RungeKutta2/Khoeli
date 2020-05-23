package models;

import java.util.LinkedList;
import java.util.List;

public class Playable{
	private int healthPoints;
	private List<String> items;
	private String name;
	private Genders gender;

	public String getName() {
		return name;
	}

	public Genders getGender() {
		return gender;
	}

	public Playable(String name, Genders gender) {
		this.name = name;
		this.gender = gender;
		healthPoints = 100;
		items = new LinkedList<String>();
	}

	public List<String> getItems() {
		return items;
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
