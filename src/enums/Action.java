package enums;

import java.util.Arrays;
import java.util.List;

public enum Action {
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

	public static Action get(String action) {
		Action result = null;
		if (MOVE.getSynonyms().contains(action) || MOVE.toString().equals(action)) {
			result = MOVE;
		} else if (PICK_UP.getSynonyms().contains(action) || PICK_UP.toString().equals(action)) {
			result = PICK_UP;
		} else if (USE.getSynonyms().contains(action) || USE.toString().equals(action)) {
			result = USE;
		} else if (LOOK_AT.getSynonyms().contains(action) || LOOK_AT.toString().equals(action)) {
			result = LOOK_AT;
		} else if (TALK_TO.getSynonyms().contains(action) || TALK_TO.toString().equals(action)) {
			result = TALK_TO;
		} else if (SAVE.getSynonyms().contains(action) || SAVE.toString().equals(action)) {
			result = SAVE;
		}
		return result;
	}
}
