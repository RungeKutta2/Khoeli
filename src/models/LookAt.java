package models;

public class LookAt implements Renombrar {

	private Renombrar next;

	@Override
	public void setNext(Renombrar renombrar) {
		this.next = renombrar;
		
	}
	@Override
	public void handle(ActionRequest request) {
		// TODO Auto-generated method stub
		
	}

}
