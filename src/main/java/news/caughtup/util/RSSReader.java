package news.caughtup.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import news.caughtup.database.ArticleDBAdapter;
import news.caughtup.model.Article;
import news.caughtup.model.NewsSource;
import news.caughtup.model.NewsSourceList;

public class RSSReader extends TimerTask {
    private NewsSourceList newsSourceList;
    
    public RSSReader(NewsSourceList newsSourceList) {
        this.newsSourceList = newsSourceList;
    }
   
    @Override
    public void run() {
        try {
            Map<String, NewsSource> newsSourcesMap = new HashMap<String, NewsSource>(); 
            for (NewsSource newsSource: newsSourceList.getNewsSources()) {
                URL feedUrl = new URL(newsSource.getRssURL());
                Date latestArticleDate = newsSource.getLatestArticleDate();
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                @SuppressWarnings("unchecked")
                List<SyndEntryImpl> feeds = feed.getEntries();
                Date newLatestArticleDate = latestArticleDate;
                for (SyndEntryImpl entry: feeds) {
                    Date articleDate = entry.getPublishedDate();
                    if ((latestArticleDate != null && articleDate.after(latestArticleDate)) 
                        || latestArticleDate == null) {
                        newLatestArticleDate = articleDate;
                        Article article = new Article(entry.getTitle(), entry.getPublishedDate(), 
                                entry.getDescription().toString(), entry.getUri());
                        // Save the new article in the Database
                        ArticleDBAdapter.saveArticle(article);
                    }
                }
                newsSource.setLatestArticleDate(newLatestArticleDate);
                newsSourcesMap.put(newsSource.getName(), newsSource);
            }
            newsSourceList.setNewsSourcesMap(newsSourcesMap);
        }catch (MalformedURLException e) {
            System.err.println("Bad RSS feed URL");
        } catch (IllegalArgumentException e) {
            System.err.println("Error");
        } catch (FeedException e) {
            System.err.println("Error parsing the RSS feed");
        } catch (IOException e) {
            System.err.println("Error while trying to parse the file");
        }
    }
}
