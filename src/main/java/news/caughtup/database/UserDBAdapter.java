package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.model.User;

public class UserDBAdapter extends DBAdapter {
	private static final int baseIndex = 1;
	/**
	 * Gets a user from the database using the <b>username</b> and returns a <b>User</b> object.
	 * @param username
	 * @return User
	 * @throws SQLException
	 */
	public static synchronized User getUser(String username) throws SQLException {
		// Get user data from database
		PreparedStatement ps = driver.getPreparedStatement("getUser");
		ps.setString(baseIndex, username);
		ArrayList<HashMap<String,Object>> result = driver.executeStatement(ps);
		if (result == null || result.size() == 0) {
			return null;
		}

		// Create the user object
		HashMap<String, Object> userData = result.get(0);
		User user = new User((String) userData.get("username"),
				(String) userData.get("password"), 
				(String) userData.get("full_name"), 
				(Integer) userData.get("age"), 
				(String) userData.get("gender"), 
				(String) userData.get("email"), 
				(String) userData.get("profile_picture_url"), 
				(String) userData.get("location"), 
				(String) userData.get("about_me"));

		return user;
	}

	/**
	 * Saves (<b>INSERT</b>s) a new user into the database.
	 * @param user
	 * @return boolean (Success)
	 * @throws SQLException
	 */
	public static synchronized void saveUser(User user) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("insertUser");
		int index = baseIndex;
		ps.setLong(baseIndex, ResourceDBAdapter.createResource());
		ps.setString(++index, user.getUsername());
		ps.setString(++index, user.getPassword());
		ps.setString(++index, user.getFullName());
		ps.executeUpdate();
	}

	/**
	 * Updates a user in the database by using the fields of a user object.
	 * @param user
	 * @throws SQLException 
	 */
	public static synchronized void updateUser(User user) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("updateUser");
		int index = baseIndex;
		ps.setString(baseIndex, user.getFullName());
		ps.setString(++index, user.getProfilePictureURL());
		ps.setInt(++index, user.getAge().intValue());
		ps.setString(++index, user.getGenderStr());
		ps.setString(++index, user.getLocation());
		ps.setString(++index, user.getEmail());
		ps.setString(++index, user.getAboutMe());
		ps.setString(++index, user.getUsername());
		ps.executeUpdate();
	}

	/**
	 * Updates a user's <b>password</b> in the database.
	 * @param user
	 * @throws SQLException 
	 */
	public static synchronized void updateUserPassword(User user) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("updateUserPassword");
		int index = baseIndex;
		ps.setString(baseIndex, user.getPassword());
		ps.setString(++index, user.getUsername());
		ps.executeUpdate();
	}


	/**
	 * Deletes a user from the database by using its <b>username</b>
	 * @param username
	 * @throws SQLException 
	 */
	public static synchronized void deleteUser(String username) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("deleteUser");
		ps.setString(baseIndex, username);
		ps.execute();
	}
}
