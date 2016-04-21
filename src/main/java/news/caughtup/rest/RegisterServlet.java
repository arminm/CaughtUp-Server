package news.caughtup.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import news.caughtup.database.UserDBAdapter;
import news.caughtup.model.User;
import news.caughtup.model.UserList;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final UserList userList = UserList.getUserList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getRequestURI().substring(req.getContextPath().length()).split("/")[2];
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        User user = (User) gson.fromJson(sb.toString(), User.class);
        user.setUsername(username);
        userList.addToUserList(user);
        UserDBAdapter.saveUser(user);
        
        PrintWriter out = resp.getWriter();
        out.println("Successfully registered user: " + user.toString());
    }
}
