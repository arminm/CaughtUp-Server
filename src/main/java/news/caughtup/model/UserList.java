package news.caughtup.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author CaughtUp
 *
 */
public class UserList {
    private static UserList userList;
    private static Map<String, User> userMap = new HashMap<>();
    
    public static UserList getUserList() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }
    
    public User getUser(String username) {
        return userMap.get(username);
    }
    
    public List<User> getUsers() {
        return new LinkedList<>(userMap.values());
    }
    
    public void addToUserList(User user) {
        userMap.put(user.getUsername(), user);
    }
    
    public void deleteFromUserList(String username) {
        userMap.remove(username);
    }
}
