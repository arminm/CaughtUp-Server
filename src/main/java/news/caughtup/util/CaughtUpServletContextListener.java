package news.caughtup.util;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import news.caughtup.database.NewsSourceDBAdapter;
import news.caughtup.model.NewsSource;
import news.caughtup.model.NewsSourceList;


/**
 * @author CaughtUp
 * Used to initialize the RSS reader thread on server startup
 */
public class CaughtUpServletContextListener implements ServletContextListener {
    private Timer timer;
    private NewsSourceList newsSourceList;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    	// When the server is stopped, kill thread and store latest article dates in DB
        timer.cancel();
        for (NewsSource newsSource: newsSourceList.getNewsSources()) {
            try {
                NewsSourceDBAdapter.updateNewsSource(newsSource);
            } catch (SQLException e) {
                System.err.println("Failed to update news source");
                System.err.println(e);
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    	/* On server startup, read the available news_source from DB
    	 * and start the RSSReader thread to run every 30 seconds
    	 */
        newsSourceList = NewsSourceList.getNewsSourceList();
        try {
            newsSourceList.setNewsSourcesMap(NewsSourceDBAdapter.getNewsSources());
        } catch (SQLException e) {
            System.err.println("Failed to get available news sources");
            System.err.println(e);
        }
        TimerTask rss = new RSSReader(newsSourceList);
        timer = new Timer(false);
        timer.scheduleAtFixedRate(rss, 0, 30*1000);
    }

}
