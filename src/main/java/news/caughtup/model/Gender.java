package news.caughtup.model;

import com.google.gson.annotations.SerializedName;

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
}