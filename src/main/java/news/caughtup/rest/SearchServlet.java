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

import news.caughtup.database.SearchDBAdapter;
import news.caughtup.model.Article;
import news.caughtup.model.NewsSource;
import news.caughtup.model.User;
import news.caughtup.util.Helpers;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * [GET] /search?keyword=&context=[user, article, news_source, all]
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String keyword = req.getParameter("keyword");
		String context = req.getParameter("context");

		try {
            HashMap<String, Object> results = new HashMap<String, Object>();

			// Send JSON response back to the client
			if (keyword == null || keyword.isEmpty()) {
				resp.setStatus(400);
				out.println(Helpers.getErrorJSON("Bad Request. Keyword cannot be null."));
			} else if (context.equals("user")) {
				ArrayList<User> users = SearchDBAdapter.searchUsers(keyword);
				results.put("users", users);
			} else if (context.equals("article")) {
				ArrayList<Article> articles = SearchDBAdapter.searchArticles(keyword);
                results.put("articles", articles);
			} else if (context.equals("news_source")) {
				ArrayList<NewsSource> newsSources = SearchDBAdapter.searchNewsSources(keyword);
                results.put("news_sources", newsSources);
			} else {
				ArrayList<User> users = SearchDBAdapter.searchUsers(keyword);
				results.put("users", users);

				ArrayList<Article> articles = SearchDBAdapter.searchArticles(keyword);
				results.put("articles", articles);

				ArrayList<NewsSource> newsSources = SearchDBAdapter.searchNewsSources(keyword);
				results.put("news_sources", newsSources);

			}
            out.println(Helpers.getGson().toJson(results));
		} catch (SQLException e) {
			System.err.println("Failed to find results for keyword: " + keyword);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		}
	}
}
