package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.database.FollowerDBAdapter;
import news.caughtup.model.Follow;
import news.caughtup.model.NewsSource;
import news.caughtup.model.User;
import news.caughtup.util.Helpers;

public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * [GET] /follow?user_id=&type=[users, news_sources, all]
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String userIdStr = req.getParameter("user_id");
		Long userId = Long.valueOf(userIdStr);
		String type = req.getParameter("type");
//		System.out.printf("UserId: %d, Type: %s\n", userId, type);
		try {
			if (type.equals("users")) {
				ArrayList<User> users = FollowerDBAdapter.getUserFollows(userId);
				// Send JSON response back to the client
				out.println(Helpers.getGson().toJson(users));
			} else if (type.equals("news_sources")) {
				ArrayList<NewsSource> newsSources = FollowerDBAdapter.getNewsSourceFollows(userId);
				// Send JSON response back to the client
				out.println(Helpers.getGson().toJson(newsSources));
			} else {
				HashMap<String, Object> resMap = new HashMap<String,Object>();
				ArrayList<User> users = FollowerDBAdapter.getUserFollows(userId);
				resMap.put("users", users);
				ArrayList<NewsSource> newsSources = FollowerDBAdapter.getNewsSourceFollows(userId);
				resMap.put("news_sources", newsSources);
				// Send JSON response back to the client
				out.println(Helpers.getGson().toJson(resMap));
			}

		} catch (SQLException e) {
			System.err.println("Failed to get followees/news sources for userId: " + userIdStr);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		}

	}

	/**
	 * [POST] /follow?user_id=&resource_id=
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String userIdStr = req.getParameter("user_id");
		Long userId = Long.valueOf(userIdStr);
		String resourceIdStr = req.getParameter("resource_id");
		Long resourceId = Long.valueOf(resourceIdStr);

		try {
			Follow follow = new Follow(userId, resourceId);
			FollowerDBAdapter.addFollower(follow);
			out.println(Helpers.getMessageJSON("Success"));
		} catch (SQLException e) {
			System.err.println("Failed to add follow for userId: " + userIdStr);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		}
	}

	/**
	 * [DELETE] /follow?user_id=&resource_id=
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String userIdStr = req.getParameter("user_id");
		Long userId = Long.valueOf(userIdStr);
		String resourceIdStr = req.getParameter("resource_id");
		Long resourceId = Long.valueOf(resourceIdStr);

		try {
			Follow follow = new Follow(userId, resourceId);
			FollowerDBAdapter.deleteFollower(follow);
			out.println(Helpers.getMessageJSON("Success"));
		} catch (SQLException e) {
			System.err.println("Failed to delete follow for userId: " + userIdStr);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		}
	}
}
