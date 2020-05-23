package models;

public class Connection {
	private Directions direction;
	private String location;
	private String obstacle;

	public Connection(Directions direction, String location, String obstacles) {
		super();
		this.direction = direction;
		this.location = location;
		this.obstacle = obstacles;
	}

	public Directions getDirection() {
		return direction;
	}

	public String getLocation() {
		return location;
	}

	public String getObstacle() {
		return obstacle;
	}

}
