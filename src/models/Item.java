package models;

public class Item implements Obstacle {
	private String id;
	private String name;
	private Genders gender;
	private Numbers number;
	private String[] actions;
	private String[] effects_over;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}
	
	public Item(String id,String name, Genders gender, Numbers number, String[] actions) {
		this.id=id;
		this.name = name;
		this.gender = gender;
		this.number = number;
		this.actions = actions;
	}

	public void clearPath() {
		// TODO Auto-generated method stub

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

	public String[] getActions() {
		return actions;
	}

	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public String[] getEffects_over() {
		return effects_over;
	}

	public void setEffects_over(String[] effects_over) {
		this.effects_over = effects_over;
	}

	public String replace(String inputParsed) {
		return inputParsed.replace(name, id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
