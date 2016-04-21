package news.caughtup.database;

import news.caughtup.model.User;

public class UserDBAdapter extends DBAdapter {

    public static synchronized User getUser(String username) {
        // TODO: return the actual user
        return null;
    }

    public static synchronized void saveUser(User user) {
        // TODO: save to DB
        System.out.println("User save in the database");
    }

    public static synchronized void updateUser(User user) {
        // TODO: update user info in DB
    }

    public static synchronized void deleteUser(String username) {
        // TODO: delete from DB
    }
}
