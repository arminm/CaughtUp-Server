package news.caughtup.database;

import java.util.List;

import news.caughtup.model.SharedArticle;

public class SharedArticleDBAdapter extends DBAdapter {

    public static synchronized List<SharedArticle> getArticle(String username) {
        // TODO: return the list of articles shared by the user named "username"
        return null;
    }

    public static synchronized void saveSharedArticle(SharedArticle sharedArticle) {
        // TODO: save to DB
    }

    public static synchronized void deleteSharedArticle(SharedArticle sharedArticle) {
        // TODO: delete article from DB
    }
}

