package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
		String username = substrings[substrings.length - 1];
		User user = (User) Helpers.getObjectFromJSON(req, User.class);
		user.setUsername(username);

		// Add user to user list and remove password for response
		boolean success = UserDBAdapter.saveUser(user);

		if (success) {
			userList.addToUserList(user);
			user.setPassword(null);	
		}

		// Send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		if (!success) {
			resp.setStatus(400);
			out.println(Helpers.getErrorJSON("Bad Request."));
		} else {
			out.println(Helpers.getGson().toJson(user));
		}
	}
}
