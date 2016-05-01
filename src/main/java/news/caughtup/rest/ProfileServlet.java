package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.database.UserDBAdapter;
import news.caughtup.model.User;
import news.caughtup.util.Helpers;

public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * [GET] /profile/:username
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // Prepare to send JSON response back to the client
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        
        String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
        String username = substrings[substrings.length - 1];
        
        // Get user from DB
        User existingUser = null;
        try {
            existingUser = UserDBAdapter.getUser(username);

            // Send JSON response back to the client
            if (existingUser == null) {
                resp.setStatus(404);
                out.println(Helpers.getErrorJSON("Internal error"));
            } else {
                out.println(Helpers.getGson().toJson(existingUser));
            }
        } catch (SQLException e) {
            System.err.println("Failed to get user with username: " + username);
            System.err.println(e);
            resp.setStatus(500);
            out.println(Helpers.getErrorJSON("Internal Error."));
        }
    }
    
    /**
     * [PUT] /profile/:username?picture={true,false}
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // Prepare to send JSON response back to the client
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        
        String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
        String username = substrings[substrings.length - 1];
        User user = (User) Helpers.getObjectFromJSON(req, User.class);
        user.setUsername(username);
        System.out.println(user.toString());
        String isPicture = req.getParameter("picture");
        if (isPicture.equals("true")) {
            out.println("Successfully updated profile picture of user: " + username);
        } else {
            // Update the info of a user in the DB
            try {
                UserDBAdapter.updateUser(user);
                out.println(Helpers.getGson().toJson(user));
            } catch (SQLException e) {
                System.err.println("Failed to update user info in DB:" + user.toString());
                System.err.println(e);
                resp.setStatus(500);
                out.println(Helpers.getErrorJSON("Internal Error."));
            }
            
        }
    }
}