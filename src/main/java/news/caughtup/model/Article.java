package news.caughtup.model;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @author CaughtUp
 *
 */
public class Article {
	private Long articleId;
	private Long resourceId;
	private String title;
	private Timestamp date;
	private String summary;
	private String articleURI;

	public Article(Long articleId, Long resourceId, String title, Timestamp date, String summary, String articleURI) {
		this.articleId = articleId;
		this.resourceId = resourceId;
		this.title = title;
		this.date = date;
		this.summary = summary;
		this.articleURI = articleURI;
	}

	public Article(HashMap<String,Object> articleData) {
		if (articleData == null || articleData.size() == 0) {
			return;
		}
		this.articleId = (Long) articleData.get("article_id");
		this.resourceId = (Long) articleData.get("resource_id");
		this.title = (String) articleData.get("title"); 
		this.date = (Timestamp) articleData.get("date"); 
		this.summary = (String) articleData.get("summary"); 
		this.articleURI = (String) articleData.get("article_url");
	}

	public synchronized Long getResourceId() {
		return resourceId == null ? 0 : resourceId;
	}
	public synchronized Long getArticleId() {
		return articleId == null ? 0 : articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getArticleURI() {
		return articleURI;
	}
	public void setArticleURI(String articleURI) {
		this.articleURI = articleURI;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("resourceId:").append(resourceId).append(",");
		sb.append("title:").append(title).append(",");
		sb.append("date:").append(date).append(",");
		sb.append("summary:").append(summary).append(",");
		sb.append("articleURI:").append(articleURI);
		return sb.toString();
	}
}
