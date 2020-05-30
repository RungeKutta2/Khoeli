package models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<Item> items;
	
	public Inventory() {
		items = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public boolean remove(Item item) {
		return items.remove(item);
	}
	
	public boolean contains(Item item) {
		return items.contains(item);
	}
}
