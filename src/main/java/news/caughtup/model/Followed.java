package news.caughtup.model;

public class Followed {
    private String followee;
    private String follower;

    public Followed(String followee, String follower) {
        this.followee = followee;
        this.follower = follower;
    }

    public String getFollowee() {
        return followee;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }
}
