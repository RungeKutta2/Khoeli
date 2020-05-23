package models;

public enum Directions {
	NORTH {
		@Override
		public String toString() {
			return "norte";
		}
	},
	SOUTH {
		@Override
		public String toString() {
			return "sur";
		}
	},
	EAST {
		@Override
		public String toString() {
			return "este";
		}
	},
	WEST {
		@Override
		public String toString() {
			return "oeste";
		}
	};
}
