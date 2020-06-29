package interfaces;

import enums.Genders;
import enums.Numbers;

public interface Observable {

	String lookAt();
	
	default String getDefineArticle(Genders gender,Numbers number) {
		String result;
		if (gender.equals(Genders.MALE)) {
			if (number.equals(Numbers.SINGULAR)) {
				result = "El";
			} else {
				result = "Los";
			}
		} else {
			if (number.equals(Numbers.SINGULAR)) {
				result = "La";
			} else {
				result = "Las";
			}
		}
		return result;
	}

	default String getUndefineArticle(Genders gender,Numbers number) {
		String result;
		if (gender.equals(Genders.MALE)) {
			if (number.equals(Numbers.SINGULAR)) {
				result = "Un";
			} else {
				result = "Unos";
			}
		} else {
			if (number.equals(Numbers.SINGULAR)) {
				result = "Una";
			} else {
				result = "Unas";
			}
		}
		return result;
	}
}
