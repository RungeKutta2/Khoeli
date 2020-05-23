package models;

public class PickUp implements Renombrar {

	private Renombrar next;

	@Override
	public void setNext(Renombrar renombrar) {
		this.next = renombrar;

	}

	@Override
	public void handle(ActionRequest request) {
		if (request.getAction().equals(TriggerActions.PICK_UP)) {

		} else {
			next.handle(request);
		}

	}

}
