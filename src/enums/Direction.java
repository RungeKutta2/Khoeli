package enums;

public enum Direction {
	NORTH {
		@Override
		public String getDescription() {
			return "norte";
		}
	},
	SOUTH {
		@Override
		public String getDescription() {
			return "sur";
		}
	},
	EAST {
		@Override
		public String getDescription() {
			return "este";
		}
	},
	WEST {
		@Override
		public String getDescription() {
			return "oeste";
		}
	};
	
	public abstract String getDescription();

	public static Direction getDirection(String callerObject) {
		switch (callerObject) {
		case "norte":
		case "n":
			return NORTH;
		case "sur":
		case "s":
			return SOUTH;
		case "este":
		case "e":	
			return EAST;
		case "oeste": 
		case "o":
			return WEST;
		default:
			return null;
		}
	}
	
}
