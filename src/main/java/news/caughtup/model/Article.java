package news.caughtup.model;

import java.sql.Timestamp;

public class Article {
	private Long articleID;
	private Long resourceID;
	private String title;
	private Timestamp date;
	private String summary;
	private String articleURI;

	public Article(Long articleID, Long resourceID, String title, Timestamp date, String summary, String articleURI) {
		this.articleID = articleID;
		this.resourceID = resourceID;
		this.title = title;
		this.date = date;
		this.summary = summary;
		this.articleURI = articleURI;
	}
	
	public synchronized Long getResourceID() {
		return resourceID == null ? 0 : resourceID;
	}
	public synchronized Long getArticleID() {
		return articleID == null ? 0 : articleID;
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
