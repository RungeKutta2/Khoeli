package interfaces;

import enums.Gender;
import enums.Number;

public interface Observable {

	String lookAt();
	
	default String getDefinedArticle(Gender gender,Number number) {
		String result;
		if (gender.equals(Gender.MALE)) {
			if (number.equals(Number.SINGULAR)) {
				result = "el";
			} else {
				result = "los";
			}
		} else {
			if (number.equals(Number.SINGULAR)) {
				result = "la";
			} else {
				result = "las";
			}
		}
		return result;
	}

	default String getUndefinedArticle(Gender gender,Number number) {
		String result;
		if (gender.equals(Gender.MALE)) {
			if (number.equals(Number.SINGULAR)) {
				result = "un";
			} else {
				result = "unos";
			}
		} else {
			if (number.equals(Number.SINGULAR)) {
				result = "una";
			} else {
				result = "unas";
			}
		}
		return result;
	}
}
