package news.caughtup.util;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import news.caughtup.database.NewsSourceDBAdapter;
import news.caughtup.model.NewsSourceList;

public class CaughtUpServletContextListener implements ServletContextListener {
    private Timer timer;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        timer.cancel();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        NewsSourceList newsSourceList = NewsSourceList.getNewsSourceList();
        try {
            newsSourceList.setNewsSourcesMap(NewsSourceDBAdapter.getNewsSources());
        } catch (SQLException e) {
            System.err.println("Failed to get available news sources");
            System.err.println(e);
        }
        TimerTask rss = new RSSReader(newsSourceList);
        timer = new Timer(false);
        timer.scheduleAtFixedRate(rss, 0, 5*1000);
    }

}
