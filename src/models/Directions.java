package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Directions {
	@JsonProperty("north")
	NORTH{
		  @Override
	        public String toString() {
	            return "norte";
	        }
	},
	@JsonProperty("south")
	SOUTH{
		  @Override
	        public String toString() {
	            return "sur";
	        }
	},
	@JsonProperty("east")
	EAST{
		  @Override
	        public String toString() {
	            return "este";
	        }
	},
	@JsonProperty("west")
	WEST{
		  @Override
	        public String toString() {
	            return "oeste";
	        }
	};
}
