package models;

public interface Executable {
	String move(Directions direction);

    String pickUp(Item item, Place place);

    String use(Item item, Triggereable affected);
    
    String use(Item item);
    
    String lookAt(Observable observable);

    String talkTo(NonPlayable npc);
}
