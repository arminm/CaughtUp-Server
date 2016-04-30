package news.caughtup.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import news.caughtup.model.NewsSource;
import news.caughtup.model.NewsSourceList;

public class TestFeed {
    public static void main(String[] args) {
        
        /* Just for testing the RSS feed */
        NewsSourceList newsSourceList = NewsSourceList.getNewsSourceList();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date date = null;
        try {
            date = formatter.parse("Fri Apr 29 13:15:20 PDT 2016");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO get the news source list from the database
        newsSourceList.addToNewsSourceList(new NewsSource(1, "cnn", "http://cnn.com", "", 
                "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml?edition=int", date));
        TimerTask rss = new RSSReader(newsSourceList);
        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(rss, 0, 5*1000);
    }
}
