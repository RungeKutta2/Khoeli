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

	public void customize(String name, Genders gender) {
		this.name=name;
		this.gender=gender;
		
	}

}
