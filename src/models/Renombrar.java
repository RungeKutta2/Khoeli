package models;

public interface Renombrar {

	void setNext(Renombrar renombrar);
	void handle(ActionRequest request);
}
