package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.model.Article;

public class ArticleDBAdapter extends DBAdapter {

	/**
	 * Saves (<b>INSERT</b>s) an article into the DB.
	 * @param article
	 * @throws SQLException
	 */
	public static synchronized void saveArticle(Article article) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("insertArticle");
		int index = baseIndex;
		System.out.println("Article: " + article.toString());
		ps.setLong(baseIndex, article.getResourceId());
		ps.setString(++index, article.getTitle());
		ps.setTimestamp(++index, article.getDate());
		ps.setString(++index, article.getSummary());
		ps.setString(++index, article.getArticleURI());
		ps.executeUpdate();
	}

	/**
	 * Updates an existing article in the database.
	 * @param article
	 * @throws SQLException
	 */
	public static synchronized void updateArticle(Article article) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("updateArticle");
		int index = baseIndex;
		ps.setString(baseIndex, article.getTitle());
		ps.setTimestamp(++index, article.getDate());
		ps.setString(++index, article.getSummary());
		ps.setString(++index, article.getArticleURI());
		ps.setLong(++index, article.getArticleId());
		ps.executeUpdate();
	}

	/**
	 * Deletes an existing article from the DB given its <b>article_id</b>.
	 * @param articleID
	 * @throws SQLException
	 */
	public static synchronized void deleteArticle(Long articleID) throws SQLException {
		if (articleID == null) {
			articleID = new Long(-1);
		}
		PreparedStatement ps = driver.getPreparedStatement("deleteArticle");
		ps.setLong(baseIndex, articleID);
		ps.executeUpdate();
	}
	
	/**
	 * Retrieves all the articles from a news source.
	 * @param source
	 * @return ArrayList<Article>
	 * @throws SQLException
	 */
	public static ArrayList<Article> getArticles(String source) throws SQLException {
		if (source == null) {
			return null;
		}
		ArrayList<Article> articles = new ArrayList<Article>();
		PreparedStatement ps = driver.getPreparedStatement("getArticle");
		ps.setString(baseIndex, source);
		ArrayList<HashMap<String, Object>> results = driver.executeStatement(ps);
		for (HashMap<String,Object> articleData : results) {
			Article article = new Article(
					(Long) articleData.get("article_id"),
					(Long) articleData.get("resource_id"),
					(String) articleData.get("title"), 
					(Timestamp) articleData.get("date"), 
					(String) articleData.get("summary"), 
					(String) articleData.get("article_url")
					);
			articles.add(article);
		}
		return articles;
	}
}
