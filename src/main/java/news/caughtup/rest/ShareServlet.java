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

import news.caughtup.database.SharedArticleDBAdapter;
import news.caughtup.model.Article;
import news.caughtup.model.SharedArticle;
import news.caughtup.util.Helpers;

public class ShareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        PrintWriter out = resp.getWriter();
        try {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        SharedArticle article = (SharedArticle) gson.fromJson(sb.toString(), SharedArticle.class);
        PrintWriter out = resp.getWriter();
        out.println("Successfully shared article " + article.getArticle() +  " of user: " + article.getUserId());
    }
}
