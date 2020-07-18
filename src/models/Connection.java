package models;

import enums.Direction;
import interfaces.Obstacle;

public class Connection {
	private Direction direction;
	private Location location;
	private Obstacle obstacle;

	public Connection(Direction direction, String location, String obstacles) {
		this.direction = direction;
		this.location = Adventure.getSelectedAdventure().findLocation(location);
		this.obstacle = Adventure.getSelectedAdventure().findObstacle(obstacles);
	}

	public Direction getDirection() {
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

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

}
