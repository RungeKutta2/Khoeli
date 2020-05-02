package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genders {
	@JsonProperty("female")
	FEMALE,
	@JsonProperty("male")
	MALE
}
