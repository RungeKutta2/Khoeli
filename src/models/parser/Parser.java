package models.parser;
import models.Adventure;

public abstract class Parser {
	private Parser next;
	private static final String defaultResult = "Acción incorrecta. Intente nuevamente.";

	public Parser linkWith(Parser next) {
		this.next = next;
		return next;
	}

	public abstract String execute(Adventure selectedAdventure, Command request);

	protected String checkNext(Adventure selectedAdventure, Command request) {
		String result = defaultResult;
		if (next != null) {
			result = next.execute(selectedAdventure, request);
		}
		return result;
	}
}
