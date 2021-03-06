package news.caughtup.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
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

/**
 * @author CaughtUp
 *
 */
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
				/* For each NewsSource get a list of articles
				 * by sending a request to the source's url
				 */
				URL feedUrl = new URL(newsSource.getRssURL());
				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new XmlReader(feedUrl));
				@SuppressWarnings("unchecked")
				List<SyndEntryImpl> feeds = feed.getEntries();
				/* Get the latest article date that we got from this source
				 * This way we avoid showing to the user old articles
				 */
				Date latestArticleDate = newsSource.getLatestArticleDate();
				if (latestArticleDate == null) {
					latestArticleDate = feeds.get(0).getPublishedDate();
				}
				// Keep track of the new latest article date based on the new articles
				Date newLatestArticleDate = latestArticleDate;
				for (SyndEntryImpl entry: feeds) {
					Date articleDate = entry.getPublishedDate();
					if (articleDate.after(latestArticleDate)) {
						// If a new article was found, store it in the DB
						if (articleDate.after(newLatestArticleDate)) {
							newLatestArticleDate = articleDate;
						}
						Timestamp articleTimestamp = new Timestamp(entry.getPublishedDate().getTime());
						Article article = new Article(null, newsSource.getResourceId(), entry.getTitle(), articleTimestamp, 
								entry.getDescription().getValue().toString(), entry.getUri());
						// Save the new article in the Database
						ArticleDBAdapter.saveArticle(article);
					}
				}
				// Update news sources with the new latest article date
				newsSource.setLatestArticleDate(new Timestamp(newLatestArticleDate.getTime()));
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
		} catch (SQLException e) {
			System.err.println("Error while saving article to DB");
			System.err.println(e);
		}
	}
}
