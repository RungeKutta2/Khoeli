package models;

public class Place {
	private String name;
	private Genders gender;
	private Numbers number;
	private Item[] items;

	public Place() {
	}

	public Place(String name, Genders gender, Numbers number, Item[] items) {
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

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
}
