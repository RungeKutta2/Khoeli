package models;

public class Subject {
	protected String name;
	public String getName() {
		return name;
	}

	public Genders getGender() {
		return gender;
	}

	protected Genders gender;

	public Subject(String name, Genders gender) {
		this.name = name;
		this.gender = gender;
	}

}
