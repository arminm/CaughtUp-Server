package news.caughtup.model;

public class User {
    private String username;
    private String fullName;
    private int age;
    private Gender gender;
    private String email;
    private String profilePictureURL;
    private String location;
    private String aboutMe;

    public User(String username, String fullName, int age, Gender gender, String email, 
            String profilePictureURL, String location, String aboutMe) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.profilePictureURL = profilePictureURL;
        this.location = location;
        this.aboutMe = aboutMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
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
}
