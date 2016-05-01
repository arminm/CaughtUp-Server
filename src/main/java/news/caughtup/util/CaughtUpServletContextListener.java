package news.caughtup.util;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import news.caughtup.database.NewsSourceDBAdapter;
import news.caughtup.model.NewsSource;
import news.caughtup.model.NewsSourceList;

public class CaughtUpServletContextListener implements ServletContextListener {
    private Timer timer;
    private NewsSourceList newsSourceList;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
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
