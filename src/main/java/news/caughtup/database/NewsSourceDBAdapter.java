package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import news.caughtup.model.NewsSource;

public class NewsSourceDBAdapter extends DBAdapter {
    
    /**
     * Returns the list of all available news sources
     * @return
     * @throws SQLException
     */
    public static synchronized Map<String, NewsSource> getNewsSources() throws SQLException {
        // Get all news sources' data from database
        PreparedStatement ps = driver.getPreparedStatement("getNewsSources");
        ArrayList<HashMap<String,Object>> result = driver.executeStatement(ps);

        // If result is empty return an empty list
        if (result == null || result.size() == 0) {
            return null;
        }
        
        // Create the NewsSource map object
        Map<String, NewsSource> newsSourcesMap = new HashMap<>();
        for (HashMap<String, Object> newsSourceData: result) {
            NewsSource newsSource = new NewsSource(
            		(Long) newsSourceData.get("resource_id"),
                    (String) newsSourceData.get("name"),
                    (String) newsSourceData.get("base_url"),
                    (String) newsSourceData.get("description"),
                    (String) newsSourceData.get("rss_url"),
                    (Timestamp) newsSourceData.get("latest_article_date"));
            newsSourcesMap.put(newsSource.getName(), newsSource);
        }
        return newsSourcesMap;
    }
    
    /**
     * Saves (<b>INSERT</b>s) a news source in the DB
     * @param newsSource
     * @return
     * @throws SQLException
     */
    public static synchronized boolean saveNewsSource(NewsSource newsSource) throws SQLException {
        PreparedStatement ps = driver.getPreparedStatement("insertNewsSource");
        int index = baseIndex;
        ps.setLong(baseIndex, ResourceDBAdapter.createResource());
        ps.setString(++index, newsSource.getName());
        ps.setString(++index, newsSource.getBaseURL());
        ps.setString(++index, newsSource.getDescription());
        ps.setString(++index, newsSource.getRssURL());
        ps.setTimestamp(++index, newsSource.getLatestArticleDate());
        ps.executeUpdate();
        System.out.printf("NewsSource saved in the database: %s\n", newsSource.toString());

        return true;
    }
}
