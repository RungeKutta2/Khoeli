package interfaces;

import enums.Directions;
import models.Item;
import models.Location;
import models.NonPlayable;
import models.Place;

public interface Executable {
	String move(Directions direction);

    String pickUp(Item item, Place place);

    String use(Item item, Triggerable affected);
    
    String use(Item item);
    
    String lookAt(Observable observable);

    String talkTo(NonPlayable npc);

	String move(Location location);
}
