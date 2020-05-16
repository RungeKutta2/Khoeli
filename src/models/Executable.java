package models;

public interface Executable {
	String move(Directions direction);

    String pickUp(String item, String place);

    String use(String item);
    
    String lookAt(String item);

}
