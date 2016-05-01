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

import news.caughtup.database.ArticleDBAdapter;
import news.caughtup.model.Article;
import news.caughtup.util.Helpers;

public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * [GET] /articles
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		String source = req.getParameter("source");

		try {
            HashMap<String, Object> result = new HashMap<String, Object>();
			// Get articles from DB
			ArrayList<Article> articles = ArticleDBAdapter.getArticles(source);
			result.put("articles", articles);

			// Send JSON response back to the client
			if (source == null) {
				resp.setStatus(400);
				out.println(Helpers.getErrorJSON("Bad Request. Source cannot be null."));
			} else {
				out.println(Helpers.getGson().toJson(result));
			}
		} catch (SQLException e) {
			System.err.println("Failed to get articles for source: " + source);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		}
	}
}
