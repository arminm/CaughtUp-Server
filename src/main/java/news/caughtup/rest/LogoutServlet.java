	package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.model.UserList;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserList userList = UserList.getUserList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        PrintWriter out = resp.getWriter();
        
        userList.deleteFromUserList(username);
        
        out.println("Successfully logged out user: " + username);
    }
}
