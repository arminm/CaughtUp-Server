package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.exception.CaughtUpServerException;
import news.caughtup.exception.CaughtUpServerExceptionFactory;
import news.caughtup.exception.ExceptionType;
import news.caughtup.exception.UserNotFoundException;
import news.caughtup.model.User;

/**
 * @author CaughtUp
 *
 */
public class UserDBAdapter extends DBAdapter {
	/**
	 * Gets a user from the database using the <b>username</b> and returns a <b>User</b> object.
	 * @param username
	 * @return User
	 * @throws SQLException
	 * @throws UserNotFoundException 
	 */
	public static synchronized User getUser(String username) throws SQLException, CaughtUpServerException {
		// Get user data from database
		PreparedStatement ps = driver.getPreparedStatement("getUser");
		ps.setString(baseIndex, username);
		ArrayList<HashMap<String,Object>> result = driver.executeStatement(ps);
		if (result == null || result.size() == 0) {
			throw CaughtUpServerExceptionFactory.createException(ExceptionType.UserNotFoundException);
		}

		// Create the user object
		HashMap<String, Object> userData = result.get(0);
		User user = new User(userData);
		if (user.getUserId() == 0) {
			throw CaughtUpServerExceptionFactory.createException(ExceptionType.UserNotFoundException);
		}
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
		ps.setString(++index, user.getGender().toString());
		ps.setString(++index, user.getLocation());
		ps.setString(++index, user.getEmail());
		ps.setString(++index, user.getAboutMe());
		ps.setString(++index, user.getUsername());
		ps.executeUpdate();
	}
	
	/**
	 * Updates a user's profile picture in the database.
	 * @param username, profilePicUrl
	 * @throws SQLException 
	 */
	public static synchronized void updateUserProfilePic(String username, String profilePicUrl) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("updateProfilePic");
		int index = baseIndex;
		ps.setString(baseIndex, profilePicUrl);
		ps.setString(++index, username);
		ps.executeUpdate();
	}

	/**
	 * Updates a user's <b>password</b> in the database.
	 * @param user
	 * @throws SQLException 
	 */
	public static synchronized void updateUserPassword(User user, String oldPassword) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("updateUserPassword");
		int index = baseIndex;
		ps.setString(baseIndex, user.getPassword());
		ps.setString(++index, user.getUsername());
		ps.setString(++index, oldPassword);
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
