package models;

public class Move implements Renombrar {

	private Renombrar next;

	@Override
	public void setNext(Renombrar renombrar) {
		this.next = renombrar;

	}

	@Override
	public void handle(ActionRequest request) {
		if (request.getAction().equals(TriggerActions.MOVE)) {

		} else {
			next.handle(request);
		}

	}

}
