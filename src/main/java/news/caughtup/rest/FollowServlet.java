package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import news.caughtup.model.Followed;

public class FollowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        String type = req.getParameter("type");
        PrintWriter out = resp.getWriter();
        if (type.equals("users")) {
            out.println("Successfully retrieved followers of user: " + username);
        } else {
            out.println("Successfully retrieved sources followed by user: " + username);
        }
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
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        Followed follower = (Followed) gson.fromJson(sb.toString(), Followed.class);
        PrintWriter out = resp.getWriter();
        out.println("Successfully deleted follower " + follower.getFollower() + " for user: " + username);
    }
}
