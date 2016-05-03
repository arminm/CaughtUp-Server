package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.caughtup.database.UserDBAdapter;
import news.caughtup.exception.CaughtUpServerException;
import news.caughtup.model.ChangePassword;
import news.caughtup.model.User;
import news.caughtup.util.Helpers;

/**
 * @author CaughtUp
 *
 */
public class PasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * [PUT] /password/:username
     * Used to update the password of a user
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Prepare to send JSON response back to the client
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        
        String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
        String username = substrings[substrings.length - 1];
        ChangePassword changePassword = (ChangePassword) Helpers.getObjectFromJSON(req, ChangePassword.class);
        User existingUser = null;
        try {
        	// Get the user info from the DB
            existingUser = UserDBAdapter.getUser(username);
            
           // If the old password provided doesn't match return unauthorized
           if (!existingUser.getPassword().equals(changePassword.getOldPassword())) {
                resp.setStatus(403);
                out.println(Helpers.getErrorJSON("Access Denied."));
            } else {
            	// Update the password with the new value and return Success
                existingUser.setPassword(changePassword.getNewPassword());
                UserDBAdapter.updateUserPassword(existingUser, changePassword.getOldPassword());
                out.println(Helpers.getMessageJSON("Success"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to get user with username: " + username);
            System.err.println(e);
            resp.setStatus(500);
            out.println(Helpers.getErrorJSON("Internal Error."));
        } catch (CaughtUpServerException e) {
			out.println(e.fix(resp));
		}
    }
}
