package news.caughtup.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import news.caughtup.model.Article;
import news.caughtup.model.NewsSource;
import news.caughtup.model.User;

public class SearchDBAdapter extends DBAdapter {
	/**
	 * Search users using a <b>keyword</b>
	 * @param keyword
	 * @return ArrayList<User>
	 * @throws SQLException
	 */
	public static synchronized ArrayList<User> searchUsers(String keyword) throws SQLException {
		// Get user data from database
		PreparedStatement ps = driver.getPreparedStatement("searchUsers");
		int index = baseIndex;
		ps.setString(baseIndex, wrapKeyword(keyword));
		ps.setString(++index, wrapKeyword(keyword));
		ArrayList<HashMap<String,Object>> result = driver.executeStatement(ps);

		ArrayList<User> searchResults = new ArrayList<User>();
		for (HashMap<String, Object> userData : result) {
			User user = new User(userData);
			if (user.getUserId() != 0) {
				searchResults.add(user);
			}
		}
		
		return searchResults;
	}
	
	/**
	 * Search articles using a <b>keyword</b>
	 * @param keyword
	 * @return ArrayList<Article>
	 * @throws SQLException
	 */
	public static synchronized ArrayList<Article> searchArticles(String keyword) throws SQLException {
		// Get user data from database
		PreparedStatement ps = driver.getPreparedStatement("searchArticles");
		int index = baseIndex;
		ps.setString(baseIndex, wrapKeyword(keyword));
		ps.setString(++index, wrapKeyword(keyword));
		ArrayList<HashMap<String,Object>> result = driver.executeStatement(ps);

		ArrayList<Article> searchResults = new ArrayList<Article>();
		for (HashMap<String, Object> articleData : result) {
			Article article = new Article(articleData);
			if (article.getResourceId() != 0) {
				searchResults.add(article);
			}
		}
		
		return searchResults;
	}
	
	/**
	 * Search news sources using a <b>keyword</b>
	 * @param keyword
	 * @return ArrayList<NewsSource>
	 * @throws SQLException
	 */
	public static synchronized ArrayList<NewsSource> searchNewsSources(String keyword) throws SQLException {
		// Get user data from database
		PreparedStatement ps = driver.getPreparedStatement("searchNewsSources");
		int index = baseIndex;
		ps.setString(baseIndex, wrapKeyword(keyword));
		ps.setString(++index, wrapKeyword(keyword));
		ArrayList<HashMap<String,Object>> result = driver.executeStatement(ps);

		ArrayList<NewsSource> searchResults = new ArrayList<NewsSource>();
		for (HashMap<String, Object> newsSourceData : result) {
			NewsSource newsSource = new NewsSource(newsSourceData);
			if (newsSource.getResourceId() != 0) {
				searchResults.add(newsSource);
			}
		}
		
		return searchResults;
	}
	
	private static String wrapKeyword(String keyword) {
		return String.format("%%%s%%", keyword);
	}
}
