package news.caughtup.model;

import java.util.Date;

public class Article {
    private String title;
    private int resourceId;
    private Date date;
    private String summary;
    private String articleURI;

    public Article(String title, int resourceId, Date date, String summary, String articleURI) {
        this.title = title;
        this.resourceId = resourceId;
        this.date = date;
        this.summary = summary;
        this.articleURI = articleURI;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
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
