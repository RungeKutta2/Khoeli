package models;

public interface Executable {
	String move(Directions direction);

    String pickUp(String item, String place);

    String use(Item item);
     
    String use(Item itemUsado,Item itemAfectado);
    
    String use(Item item,NonPlayable npc);
    
    String use(Item item, Place place);
    
    String lookAt(Item item);

    String lookAt(NonPlayable npc);

    String lookAt(Location location);
    
    String talkTo(NonPlayable npc);
    
    
}
