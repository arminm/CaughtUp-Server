package news.caughtup.model;

import java.util.HashMap;

public class User {
    private static final String SEPARATOR=",";
    private Long userId;
    private Long resourceId;
    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private Gender gender;
    private String email;
    private String profilePictureURL;
    private String location;
    private String aboutMe;

    public User(Long userId, Long resourceId, String username, String password, String fullName, Integer age, String gender, 
            String email, String profilePictureURL, String location, String aboutMe) {
    	this.userId = userId;
    	this.resourceId = resourceId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.gender = Gender.parseGenderString(gender);
        this.email = email;
        this.profilePictureURL = profilePictureURL;
        this.location = location;
        this.aboutMe = aboutMe;
    }
    
    public User(HashMap<String,Object> userData) {
    	if (userData == null || userData.size() == 0) {
    		return;
    	}
    	
    	this.userId = (Long) userData.get("user_id");
    	this.resourceId = (Long) userData.get("resource_id");
        this.username = (String) userData.get("username");
        this.password = (String) userData.get("password");
        this.fullName = (String) userData.get("full_name");
        this.age = (Integer) userData.get("age");
        this.gender = Gender.parseGenderString((String) userData.get("gender"));
        this.email = (String) userData.get("email");
        this.profilePictureURL = (String) userData.get("profile_picture_url");
        this.location = (String) userData.get("location");
        this.aboutMe = (String) userData.get("about_me");
    }

    public synchronized Long getUserId() {
		return userId == null ? 0 : userId;
	}

	public synchronized Long getResourceId() {
		return resourceId == null ? 0 : resourceId;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }
    
    public String getGenderStr() {
        return Gender.getGenderString(this.gender);
    }
    
    public void setGenderStr(String genderStr) {
        this.gender = Gender.parseGenderString(genderStr);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("username:").append(username).append(SEPARATOR);
        sb.append("password:").append(password).append(SEPARATOR);
        sb.append("fullName:").append(fullName).append(SEPARATOR);
        sb.append("age:").append(age).append(SEPARATOR);
        sb.append("gender:").append(gender).append(SEPARATOR);
        sb.append("email:").append(email).append(SEPARATOR);
        sb.append("profile_picture_url:").append(profilePictureURL).append(SEPARATOR);
        sb.append("location:").append(location).append(SEPARATOR);
        sb.append("about_me:").append(aboutMe);
        return sb.toString();
    }
}
