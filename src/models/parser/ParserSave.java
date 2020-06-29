package models.parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import enums.TriggerAction;
import models.Adventure;

public class ParserSave extends Parser{

	@Override
	public String execute(Adventure selectedAdventure, Command request) {
		String resultado;
		TriggerAction action = request.getAction();

		if (action == TriggerAction.SAVE) {
			String string = request.getCallerObject();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter("./partidas guardadas/" + string + ".txt"));
				writer.append(selectedAdventure.getHistory());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
			resultado = "Se ha guardado exitosamente.";		}
		else {
			resultado = checkNext(selectedAdventure, request);
		}
		return resultado;
	}

}
