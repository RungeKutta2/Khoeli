package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Types {
	@JsonProperty("item")
	ITEM,
	@JsonProperty("action")
	ACTION
}
