package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import news.caughtup.database.UserDBAdapter;
import news.caughtup.model.User;
import news.caughtup.util.Helpers;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
		String username = substrings[substrings.length - 1];
		User user = (User) Helpers.getObjectFromJSON(req, User.class);
		user.setUsername(username);

		// Get user from DB
		User existingUser = UserDBAdapter.getUser(username);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		// Send JSON response back to the client
		if (existingUser == null) {
			resp.setStatus(404);
			out.println(Helpers.getErrorJSON("Not Found."));
		} else if (existingUser.getPassword() != user.getPassword()) {
			resp.setStatus(403);
			out.println(Helpers.getErrorJSON("Access Denied."));
		} else {
			out.println(Helpers.getGson().toJson(user));
		}
	}
}
