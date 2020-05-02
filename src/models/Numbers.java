package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Numbers {
	@JsonProperty("plural")
	PLURAL,
	@JsonProperty("singular")
	SINGULAR
}
