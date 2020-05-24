package models;

public class Connection {
	private Directions direction;
	private Location location;
	private Obstacle obstacle;

	public Connection(Directions direction, String location, String obstacles) {
		this.direction = direction;
		this.location = Adventure.getSelectedAdventure().findLocation(location);
		this.obstacle = Adventure.getSelectedAdventure().findObstacle(obstacles); //ESTO HAY QUE MEJORARLO
	}

	public Directions getDirection() {
		return direction;
	}

	public Location getLocation() {
		return location;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void removeObstacle() {
		obstacle = null;
	}
}
