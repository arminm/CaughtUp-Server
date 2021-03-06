package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.database.UserDBAdapter;
import news.caughtup.exception.CaughtUpServerException;
import news.caughtup.exception.UserNotFoundException;
import news.caughtup.model.User;
import news.caughtup.model.UserList;
import news.caughtup.util.Helpers;

/**
 * @author CaughtUp
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserList userList = UserList.getUserList();
	
	/**
	 * [POST] /login/:username
	 * Used to login a user
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
		String username = substrings[substrings.length - 1];
		User user = (User) Helpers.getObjectFromJSON(req, User.class);
		// Get the user info from the DB using the username we got
		user.setUsername(username);
		// Get user from DB
		User existingUser = null;
		try {
			existingUser = UserDBAdapter.getUser(username);
			
			// If the password doesn't match return 403
			if (!existingUser.getPassword().equals(user.getPassword())) {
				resp.setStatus(403);
				out.println(Helpers.getErrorJSON("Access Denied."));
			} else {
			    existingUser.setPassword(null);
			    userList.addToUserList(existingUser);
			    // Send JSON response back to the client
				out.println(Helpers.getGson().toJson(existingUser));
			}
		} catch (SQLException e) {
			System.err.println("Failed to get user with username: " + username);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		} catch (CaughtUpServerException e) {
			out.println(e.fix(resp));
		}
	}
}
