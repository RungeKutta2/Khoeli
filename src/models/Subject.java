package models;

public class Subject {
	private String name;
	private Genders gender;

	public Subject() {
	}

	public Subject(String name, Genders gender) {
		this.name = name;
		this.gender = gender;
	}

	public Subject(String name) {
		this.name = name;
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
}
