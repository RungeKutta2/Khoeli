package models;

public class Connection {
	private Directions direction;
	private String location;
	private String obstacles;

	public Connection(Directions direction, String location, String obstacles) {
		super();
		this.direction = direction;
		this.location = location;
		this.obstacles = obstacles;
	}

	public Directions getDirection() {
		return direction;
	}

	public String getLocation() {
		return location;
	}

}
