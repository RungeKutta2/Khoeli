package models;

import java.util.ArrayList;
import java.util.List;

import enums.Genders;
import enums.Numbers;
import interfaces.Observable;

public class Inventory implements Observable {
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

	@Override
	public String lookAt() {
		StringBuilder sb = new StringBuilder();
		for (Item item : items) {
			sb.append("- ");
			sb.append(getUndefineArticle(item.getGender(), item.getNumber()) + " ");
			sb.append(item.getName());
			sb.append(System.lineSeparator());
		}
		int last = sb.lastIndexOf(System.lineSeparator());
		if (last >= 0) {
			sb.delete(last, sb.length());
		}
		return sb.toString();
	}
}
