package news.caughtup.database;

import java.util.List;

import news.caughtup.model.Followed;

public class FollowerDBAdapter extends DBAdapter {
	
	public static synchronized List<Followed> getFollowers(Followed followed) {
		// TODO: return the list of followers for a user
		return null;
	}
	
	public static synchronized void addFollower(Followed followed) {
		// TODO: save follower to DB
	}
	
	public static synchronized void deleteFollower(Followed followed) {
		// TODO: delete follower from DB
	}
}

