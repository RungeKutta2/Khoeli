package models;

public class TalkTo implements Renombrar {
	private Renombrar next;

	@Override
	public void setNext(Renombrar renombrar) {
		this.next = renombrar;

	}

	@Override
	public void handle(ActionRequest request) {
		if (request.getAction().equals(TriggerActions.TALK_TO)) {

		} else {
			next.handle(request);
		}

	}

}
