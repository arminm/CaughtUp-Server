package news.caughtup.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author CaughtUp
 *
 */
public class NewsSourceList {
    private static NewsSourceList newsSourceList;
    private static Map<String, NewsSource> newsSourcesMap = new HashMap<>();
    
    public static NewsSourceList getNewsSourceList() {
        if (newsSourceList == null) {
            newsSourceList = new NewsSourceList();
        }
        return newsSourceList;
    }
    
    public void setNewsSourcesMap(Map<String, NewsSource> newMap) {
        newsSourcesMap = newMap;
    }
    
    public NewsSource getNewsSource(String source) {
        return newsSourcesMap.get(source);
    }
    
    public List<NewsSource> getNewsSources() {
        return new LinkedList<NewsSource>(newsSourcesMap.values());
    }
    
    public void addToNewsSourceList(NewsSource newsSource) {
        newsSourcesMap.put(newsSource.getName(), newsSource);
    }
    
    public void deleteFromUserList(String source) {
        newsSourcesMap.remove(source);
    }
}
