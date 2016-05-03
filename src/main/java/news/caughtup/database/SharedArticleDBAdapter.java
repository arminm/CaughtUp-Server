package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.model.Article;
import news.caughtup.model.SharedArticle;

/**
 * @author CaughtUp
 *
 */
public class SharedArticleDBAdapter extends DBAdapter {

    /**
     * Returns an ArrayList<Article> for a given userId
     * @param userId
     * @return
     * @throws SQLException
     */
    public static synchronized ArrayList<Article> getArticles(Long userId) throws SQLException {
        if (userId == null) {
            return null;
        }
        ArrayList<Article> sharedArticles = new ArrayList<>();
        PreparedStatement ps = driver.getPreparedStatement("getSharedArticles");
        ps.setLong(baseIndex, userId);
        ArrayList<HashMap<String, Object>> results = driver.executeStatement(ps);
        
        for (HashMap<String,Object> sharedArticleData : results) {
            Article article = new Article(sharedArticleData);
            sharedArticles.add(article);
        }
        return sharedArticles;
    }

    /**
     * Saves an Article shared by a user
     * @param sharedArticle
     * @throws SQLException
     */
    public static synchronized void saveSharedArticle(SharedArticle sharedArticle) throws SQLException {
        if (sharedArticle != null && sharedArticle.getUserId() != null && sharedArticle.getArticleId() != null) {
            PreparedStatement ps = driver.getPreparedStatement("shareArticle");
            int index = baseIndex;
            ps.setLong(baseIndex, sharedArticle.getUserId());
            ps.setLong(++index, sharedArticle.getArticleId());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes an shared article (right now not supported)
     * @param sharedArticle
     */
    public static synchronized void deleteSharedArticle(SharedArticle sharedArticle) {
        // TODO: delete article from DB
    }
}

