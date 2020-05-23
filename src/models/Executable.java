package models;

public interface Executable {
	String move(Directions direction);

    String pickUp(String item, String place);

    String use(Item item, Triggereable affected);
    
    String use(Item item);
    
    String lookAt(Observable observable);

    String talkTo(NonPlayable npc);
}
