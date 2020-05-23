package models;

public class Khoeli implements Executable {
	private Adventure selectedAdventure;
	private Location currentLocation;

	public Adventure getSelectedAdventure() {
		return selectedAdventure;
	}

	public void setSelectedAdventure(Adventure selectedAdventure) {
		this.selectedAdventure = selectedAdventure;
		this.currentLocation = selectedAdventure.getLocations().get(0);
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}


	public String move(Directions direction) {
		String obstacle = currentLocation.findObstacle(direction);
		if (obstacle == null) {
			String locationName = currentLocation.findLocationName(direction);
			Location foundLocation = selectedAdventure.findLocation(locationName);
			if (foundLocation != null) {
				currentLocation = foundLocation;
				String endGame = selectedAdventure.findEndGame("move", "location", locationName);
				if (endGame == null) {
					return currentLocation.getDescription();
				} else {
					return endGame;
				}
			} else {
				return "No hay nada al " + direction.toString();
			}
		} else {
			return selectedAdventure.findNpc(obstacle).getDescription();
		}
	}

	public String pickUp(String item, String place) {
		String itemString = currentLocation.takeItem(item, place);
		if (itemString != null) {
			return "Juntaste " + itemString;
		}
		return "No hay " + item + " en " + place;
	}

	@Override
	public String lookAt(Item item) {
		return item.getDescription();
	}

	@Override
	public String lookAt(NonPlayable npc) {
		return npc.getDescription();
	}

	@Override
	public String lookAt(Location location) {
		return location.getDescription();
	}
	
	@Override
	public String talkTo(NonPlayable npc) {
		String talk = npc.getTalk();
		if (talk.isEmpty()) {
			return "No tiene nada que decir";
		}
		return talk;
	}

	@Override
	public String use(Item item) {
		String response = null;
		Trigger found = item.findTrigger(Types.ACTION, TriggerActions.USE.toString());
		if (found != null) {
			response = found.getOnTrigger();
			AfterTrigger afterTrigger = found.getAfterTrigger();
			switch (afterTrigger.getAction()) {
			case REMOVE:
				selectedAdventure.getSettings().getCharacter().removeItem(afterTrigger.getThing());
				break;
			case CHANGE_DESCRIPTION:
				item.changeDescription(afterTrigger.getThing());
				break;
			case ADD:
				selectedAdventure.getSettings().getCharacter().addItem(afterTrigger.getThing());
				break;
			default:
				break;
			}
		} else {
			response = "No se puede usar " + item.getName();
		}
		return response;
	}

	@Override
	public String use(Item item, Triggereable affected) {
		String response = null;
		Trigger found = affected.findTrigger(Types.ITEM, item.getId());
		if (found != null) {
			response = found.getOnTrigger();
			AfterTrigger afterTrigger = found.getAfterTrigger();
			switch (afterTrigger.getAction()) {
			case REMOVE:
				selectedAdventure.getSettings().getCharacter().removeItem(afterTrigger.getThing());
				break;
			case CHANGE_DESCRIPTION:
				affected.changeDescription(afterTrigger.getThing());
				break;
			case ADD:
				selectedAdventure.getSettings().getCharacter().addItem(afterTrigger.getThing());
				break;
			default:
				break;
			}
		} else {
			response = "No se puede usar " + item.getName() + " con " + affected.getName();
		}
		return response;
	}
}
