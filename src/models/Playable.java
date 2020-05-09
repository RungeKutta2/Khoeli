package models;

import java.util.LinkedList;
import java.util.List;

public class Playable extends Subject {
	private int healthPoints;
	private List<String> items;

	public Playable() {
	}

	public Playable(String name, Genders gender) {
		super(name, gender);
		healthPoints = 100;
		items = new LinkedList<String>();
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

}
