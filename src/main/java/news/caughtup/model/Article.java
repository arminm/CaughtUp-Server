package news.caughtup.model;

import java.sql.Timestamp;

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
}
