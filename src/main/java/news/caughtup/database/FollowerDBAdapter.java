package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.model.Follow;
import news.caughtup.model.User;

public class FollowerDBAdapter extends DBAdapter {
	
	/**
	 * Retrieves the list of followers (<b>User</b>s) following a resource using its <b>resource_id</b>
	 * @param resourceId
	 * @return List<User>
	 * @throws SQLException
	 */
    public static synchronized ArrayList<User> getFollowers(Long resourceId) throws SQLException {
    	if (resourceId == null) {
			return null;
		}
		ArrayList<User> followers = new ArrayList<User>();
		PreparedStatement ps = driver.getPreparedStatement("getFollowers");
		ps.setLong(baseIndex, resourceId);
		ArrayList<HashMap<String, Object>> results = driver.executeStatement(ps);
		
		for (HashMap<String,Object> userData : results) {
			User user = new User(userData);
			followers.add(user);
		}
		return followers;
    }

    /**
     * Adds a follower (<b>User</b>) to a resource.
     * @param follow
     * @throws SQLException
     */
    public static synchronized void addFollower(Follow follow) throws SQLException {
    	PreparedStatement ps = driver.getPreparedStatement("insertFollow");
		int index = baseIndex;
		ps.setLong(baseIndex, follow.getUserId());
		ps.setLong(++index, follow.getResourceId());
		ps.executeUpdate();
    }

    /**
     * Removes a follower from a resource by removing an entire entry from the <b>follower_following</b> join table.
     * @param follow
     * @throws SQLException
     */
    public static synchronized void deleteFollower(Follow follow) throws SQLException {
    	PreparedStatement ps = driver.getPreparedStatement("deleteFollow");
		int index = baseIndex;
		ps.setLong(baseIndex, follow.getUserId());
		ps.setLong(++index, follow.getResourceId());
		ps.executeUpdate();
    }
}

