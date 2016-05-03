package news.caughtup.model;

import com.google.gson.annotations.SerializedName;


/**
 * @author CaughtUp
 * Enum for gender type
 */
public enum Gender {
	@SerializedName("male")
    MALE("male"),
    
    @SerializedName("female")
    FEMALE("female"), 
    
    @SerializedName("none")
    NONE("none");
    
	private final String gender;
    public String toString() {
        return gender;
    }

    private Gender(String gender) {
        this.gender = gender;
    }
    
    public static Gender getGender(String gender) {
        if (gender == null) {
            return NONE;
        }
        String genderLowerCase = gender.toLowerCase();
        switch(genderLowerCase) {
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                return NONE;
        }
    }
}