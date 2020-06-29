package enums;

import java.util.Arrays;
import java.util.List;

public enum TriggerAction {
	MOVE {
		@Override
		public List<String> getSynonyms() {
			String[] synonyms = new String[] { "ir", "moverse", "moverme", "dirigirse", "dirigirme", "desplazarse",
					"desplazarme" };
			return Arrays.asList(synonyms);
		}
	},
	PICK_UP {
		@Override
		public List<String> getSynonyms() {
			String[] synonyms = new String[] { "agarrar", "juntar", "recoger", "tomar", "coger" };
			return Arrays.asList(synonyms);
		}
	},
	USE {
		@Override
		public List<String> getSynonyms() {
			String[] synonyms = new String[] { "usar", "utilizar", "emplear" };
			return Arrays.asList(synonyms);
		}
	},
	LOOK_AT {
		@Override
		public List<String> getSynonyms() {
			String[] synonyms = new String[] { "mirar", "observar", "leer", "ver", "ojear", "contemplar" };
			return Arrays.asList(synonyms);
		}
	},
	TALK_TO {
		@Override
		public List<String> getSynonyms() {
			String[] synonyms = new String[] { "hablar", "conversar", "charlar", "platicar", "discutir" };
			return Arrays.asList(synonyms);
		}
	},
	SAVE {
		@Override
		public List<String> getSynonyms() {
			String[] synonyms = new String[] { "guardar", "salvar", "grabar" };
			return Arrays.asList(synonyms);
		}
	};

	public abstract List<String> getSynonyms();

	public static TriggerAction getTriggerAction(String action) {
		TriggerAction result = null;
		if (MOVE.getSynonyms().contains(action)) {
			result = MOVE;
		} else if (PICK_UP.getSynonyms().contains(action)) {
			result = PICK_UP;
		} else if (USE.getSynonyms().contains(action)) {
			result = USE;
		} else if (LOOK_AT.getSynonyms().contains(action)) {
			result = LOOK_AT;
		} else if (TALK_TO.getSynonyms().contains(action)) {
			result = TALK_TO;
		} else if (SAVE.getSynonyms().contains(action)) {
			result = SAVE;
		}
		return result;
	}
}
