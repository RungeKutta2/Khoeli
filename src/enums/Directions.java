package enums;

public enum Directions {
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
	
}
