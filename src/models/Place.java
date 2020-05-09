package models;

public class Place {
	private String name;
	private Genders gender;
	private Numbers number;
	private String[] items;

	public Place() {
	}

	public Place(String name, Genders gender, Numbers number, String[] items) {
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

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
}
