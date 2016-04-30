package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import news.caughtup.database.ArticleDBAdapter;
import news.caughtup.model.Article;
import news.caughtup.model.Follow;
import news.caughtup.util.Helpers;

public class FollowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
	 * [GET] /follow
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
//        String resource_id = req.getParameter("resource_id");
//        PrintWriter out = resp.getWriter();
//        if (type.equals("users")) {
//            out.println("Successfully retrieved followers of user: " + username);
//        } else {
//            out.println("Successfully retrieved sources followed by user: " + username);
//        }
//        
//        
//     // Prepare to send JSON response back to the client
//     		resp.setContentType("application/json");
//     		PrintWriter out = resp.getWriter();
//     		
//     		String source = req.getParameter("source");
//
//     		try {
//     			// Get articles from DB
//     			ArrayList<Article> articles = ArticleDBAdapter.getArticles(source);
//
//     			// Send JSON response back to the client
//     			if (source == null) {
//     				resp.setStatus(400);
//     				out.println(Helpers.getErrorJSON("Bad Request. Source cannot be null."));
//     			} else {
//     				out.println(Helpers.getGson().toJson(articles));
//     			}
//     		} catch (SQLException e) {
//     			System.err.println("Failed to get articles for source: " + source);
//     			System.err.println(e);
//     			resp.setStatus(500);
//     			out.println(Helpers.getErrorJSON("Internal Error."));
//     		}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        String follower = req.getParameter("follower");
        PrintWriter out = resp.getWriter();
        out.println("Successfully added follower " + follower + " for user: " + username);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
//        Gson gson = new Gson();
//        StringBuilder sb = new StringBuilder();
//        String s;
//        while ((s = req.getReader().readLine()) != null) {
//            sb.append(s);
//        }
//        Follow follower = (Follow) gson.fromJson(sb.toString(), Follow.class);
//        PrintWriter out = resp.getWriter();
//        out.println("Successfully deleted follower " + follower.getFollower() + " for user: " + username);
    }
}
