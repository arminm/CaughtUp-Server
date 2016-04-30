package news.caughtup.util;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import news.caughtup.database.NewsSourceDBAdapter;
import news.caughtup.model.NewsSource;
import news.caughtup.model.NewsSourceList;

public class TestFeed {
	public static void main(String[] args) {

		/* Insert test news sources */
		try {
			NewsSourceDBAdapter.saveNewsSource(new NewsSource(null, "cnn", "http://www.cnn.com", "CNN news", 
					"http://rss.cnn.com/rss/cnn_topstories.rss", new Timestamp(1)));
			NewsSourceDBAdapter.saveNewsSource(new NewsSource(null, "bbc", "http://www.bbc.com", "BBC news", 
					"http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml?edition=int", new Timestamp(1)));
		} catch (SQLException e) {
			System.err.println("Failed while trying to insert a news source");
			System.err.println(e);
		}

		/* Just for testing the RSS feed */
		NewsSourceList newsSourceList = NewsSourceList.getNewsSourceList();
		try {
			newsSourceList.setNewsSourcesMap(NewsSourceDBAdapter.getNewsSources());
		} catch (SQLException e) {
			System.err.println("Failed to get available news sources");
			System.err.println(e);
		}
		/*SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date date = null;
        try {
            date = formatter.parse("Fri Apr 29 13:15:20 PDT 2016");
        } catch (ParseException e) {

        }
        newsSourceList.addToNewsSourceList(new NewsSource(1, "cnn", "http://cnn.com", "", 
                "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml?edition=int", date));
		 */
		TimerTask rss = new RSSReader(newsSourceList);
		Timer timer = new Timer(false);
		timer.scheduleAtFixedRate(rss, 0, 5*1000);
	}
}
