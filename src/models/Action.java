package models;

import java.util.Set;

public class Action {
	private String name;
	private Set<String> synonyms;

	public Action() {
	}

	public Action(String name) {
		this.name = name;
	}
	
	public Action(String name, Set<String> synonyms) {
		this.name = name;
		this.synonyms = synonyms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<String> synonyms) {
		this.synonyms = synonyms;
	}
}
