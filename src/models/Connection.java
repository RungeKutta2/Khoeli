package models;

public class Connection {
	private Directions direction;
	private Location location;
	private NonPlayable obstacles;

	public Connection() {
	}

	public Directions getDirection() {
		return direction;
	}

	public void setDirection(Directions direction) {
		this.direction = direction;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public NonPlayable getObstacles() {
		return obstacles;
	}

	public void setObstacles(NonPlayable obstacles) {
		this.obstacles = obstacles;
	}
}
