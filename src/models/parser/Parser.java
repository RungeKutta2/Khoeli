package models.parser;
import java.text.Normalizer;

import models.Adventure;
import models.Item;
import models.Location;
import models.NonPlayable;

public abstract class Parser {
	private Parser next;
	private static final String defaultResult = "Acción incorrecta. Intente nuevamente.";
	private static final String conectors = " (el|los|la|las|un|una|unos|unas|al|del|a|ante|bajo|con|contra|de|desde|durante|en|entre|hacia|hasta|mediante|para|por|segun|sin|sobre|tras) ";

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
	

	public static Command parse(String next) {
		String inputNormalized = Normalizer.normalize(next.replaceAll("\\s+", " ").trim().toLowerCase(), Normalizer.Form.NFD)
				.replaceAll("[\\u0300-\\u0301]", "");
		String inputParsed = Normalizer.normalize(inputNormalized, Normalizer.Form.NFC);
		inputParsed = replaceId(inputParsed);
		inputParsed = removeConectors(inputParsed);
		String[] parsed = inputParsed.split(" ");

		Command comando = null;
		try {
			comando = new Command(parsed[0], parsed[1], parsed.length == 3 ? parsed[2] : null);
		} catch (Exception e) {
			System.out.println("Acción incorrecta, intente nuevamente.");
		}
		return comando;
	}

	private static String removeConectors(String name) {
		String actual = name;
		boolean distinto = true;
		do {
			actual = name.replaceFirst(conectors, " ");
			if (actual == name) {
				distinto = false;
			} else {
				name = actual;
			}
		} while (distinto);

		return name;
	}

	private static String replaceId(String name) {
		Adventure selectedAdventure= Adventure.getSelectedAdventure();
		String replaced = name;
		for (NonPlayable npc : selectedAdventure.getNpcs()) {
			replaced = replaced.replaceAll(npc.getName(), npc.getId());
		}
		for (Item item : selectedAdventure.getItems()) {
			replaced = replaced.replaceAll(item.getName(), item.getId());
		}
		for (Location location : selectedAdventure.getLocations()) {
			replaced = replaced.replaceAll(location.getName(), location.getId());
		}
		return replaced;
	}
}
