package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.database.UserDBAdapter;
import news.caughtup.model.User;
import news.caughtup.model.UserList;
import news.caughtup.util.Helpers;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserList userList = UserList.getUserList();

	/**
	 * [POST] /register/:username
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
		String username = substrings[substrings.length - 1];
		User user = (User) Helpers.getObjectFromJSON(req, User.class);
		user.setUsername(username);

		User existingUser = null;
		try {
			//Try to get user from DB
			existingUser = UserDBAdapter.getUser(username);
			
			// If user exists, the username is taken
			if (existingUser != null) {
				resp.setStatus(403);
				out.println(Helpers.getErrorJSON("Username taken."));
			} else {
				// Add user to user list and remove password for response
				UserDBAdapter.saveUser(user);
				userList.addToUserList(user);
				user.setPassword(null);
				out.println(Helpers.getGson().toJson(user));
			}
		} catch (SQLException e) {
			System.err.println("Failed to save user in DB:" + user.toString());
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error. Make sure username is unique."));
		}
	}
}
