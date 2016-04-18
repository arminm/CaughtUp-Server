package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        PrintWriter out = resp.getWriter();
        out.println("Successfully got profile of user: " + username);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        String isPicture = req.getParameter("picture");
        PrintWriter out = resp.getWriter();
        if (isPicture.equals("true")) {
            out.println("Successfully updated profile picture of user: " + username);
        } else {
            out.println("Successfully updated profile info of user: " + username);
        }
    }
}