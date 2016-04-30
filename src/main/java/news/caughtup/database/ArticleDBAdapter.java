package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.model.Article;

public class ArticleDBAdapter extends DBAdapter {
	private static final int baseIndex = 1;

	/**
	 * Saves (<b>INSERT</b>s) an article into the DB.
	 * @param article
	 * @throws SQLException
	 */
	public static synchronized void saveArticle(Article article) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("insertArticle");
		int index = baseIndex;
		ps.setInt(baseIndex, ResourceDBAdapter.createResource());
		ps.setString(++index, article.getTitle());
		ps.setString(++index, article.getDate());
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
		ps.setString(++index, article.getDate());
		ps.setString(++index, article.getSummary());
		ps.setString(++index, article.getArticleURI());
		ps.setInt(++index, article.getArticleID());
		ps.executeUpdate();
	}

	/**
	 * Deletes an existing article from the DB given its <b>article_id</b>.
	 * @param articleID
	 * @throws SQLException
	 */
	public static synchronized void deleteArticle(Integer articleID) throws SQLException {
		PreparedStatement ps = driver.getPreparedStatement("deleteArticle");
		ps.setInt(baseIndex, articleID);
		ps.execute();
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
					(Integer) articleData.get("article_id"),
					(String) articleData.get("title"), 
					(String) articleData.get("date"), 
					(String) articleData.get("summary"), 
					(String) articleData.get("article_url")
					);
			articles.add(article);
		}
		return articles;
	}
}
