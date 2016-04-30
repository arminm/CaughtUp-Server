package news.caughtup.model;

public enum Gender {
	MALE, FEMALE, NONE;

	public static Gender parseGenderString(String gender) {
		if (gender == null) {
			return Gender.NONE;
		}
		String lowerCaseGender = gender.toLowerCase();
		if (lowerCaseGender.equals("male") || lowerCaseGender.equals("m")) {
			return Gender.MALE;
		} else if (lowerCaseGender.equals("female") || lowerCaseGender.equals("f")) {
			return Gender.FEMALE;
		} else {
			return Gender.NONE;
		}
	}

	public static String getGenderString(Gender gender) {
		switch (gender) {
		case MALE:
			return "male";
		case FEMALE:
			return "female";
		default:
			return null;
		}
	}
}