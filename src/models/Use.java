package models;

public class Use implements Renombrar {

	private Renombrar next;

	@Override
	public void setNext(Renombrar renombrar) {
		this.next = renombrar;
		
	}

	@Override
	public void handle(ActionRequest request) {
		if(request.getAction().equals(TriggerActions.USE)) {
			
		}else {
			next.handle(request);
		}
		
	}

}
