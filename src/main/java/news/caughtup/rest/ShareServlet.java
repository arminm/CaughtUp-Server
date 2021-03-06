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

import com.google.gson.Gson;

import news.caughtup.database.FollowerDBAdapter;
import news.caughtup.database.SharedArticleDBAdapter;
import news.caughtup.model.Article;
import news.caughtup.model.SharedArticle;
import news.caughtup.util.Helpers;

/**
 * @author CaughtUp
 *
 */
public class ShareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * [GET] /share?user_id=
     * Used to get articles that have been shared by a user
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        PrintWriter out = resp.getWriter();
        try {
        	// Get articles shared from DB
            ArrayList<Article> sharedArticles = SharedArticleDBAdapter.getArticles(userId);
            HashMap<String, Object> articlesMap = new HashMap<>();
            articlesMap.put("articles", sharedArticles);
            out.println(Helpers.getGson().toJson(articlesMap));
        } catch (SQLException e) {
            System.err.println("Failed to get shared articles for userId: " + Long.toString(userId));
            System.err.println(e);
            resp.setStatus(500);
            out.println(Helpers.getErrorJSON("Internal Error."));
        }
    }
    /**
     * [POST] /share?user_id=&article_id=
     * Used to share a new article from a user
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        Long articleId = Long.valueOf(req.getParameter("article_id"));
        SharedArticle sharedArticle = new SharedArticle();
        sharedArticle.setUserId(userId);
        sharedArticle.setArticleId(articleId);
        PrintWriter out = resp.getWriter();
        try {
        	// Store the article being shared in DB
            SharedArticleDBAdapter.saveSharedArticle(sharedArticle);
            out.println(Helpers.getMessageJSON("Success"));
        } catch (SQLException e) {
            System.err.println("Failed to share articleId " + Long.toString(articleId) + " from userId " + Long.toString(userId));
            System.err.println(e);
            resp.setStatus(500);
            out.println(Helpers.getErrorJSON("Internal Error."));
        }
    }
}
