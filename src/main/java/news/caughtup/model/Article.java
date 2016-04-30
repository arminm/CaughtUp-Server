package news.caughtup.model;

public class Article {
	private Integer articleID;
	private String title;
	private String date;
	private String summary;
	private String articleURI;

	public Article(Integer articleID, String title, String date, String summary, String articleURI) {
		this.title = title;
		this.date = date;
		this.summary = summary;
		this.articleURI = articleURI;
	}

	public synchronized Integer getArticleID() {
		return articleID;
	}
	public synchronized void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
