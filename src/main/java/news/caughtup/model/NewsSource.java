package news.caughtup.model;

import java.util.Date;

public class NewsSource {
    private int resourceId;
    private String name;
    private String baseURL;
    private String description;
    private String rssURL;
    private Date latestArticleDate;
    
    public NewsSource(int resourceId, String name, String baseURL, String description, String rssURL, Date latestArticleDate) {
        super();
        this.resourceId = resourceId;
        this.name = name;
        this.baseURL = baseURL;
        this.description = description;
        this.rssURL = rssURL;
        this.latestArticleDate = latestArticleDate;
    }
    
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRssURL() {
        return rssURL;
    }

    public void setRssURL(String rssURL) {
        this.rssURL = rssURL;
    }

    public Date getLatestArticleDate() {
        return latestArticleDate;
    }

    public void setLatestArticleDate(Date latestArticleDate) {
        this.latestArticleDate = latestArticleDate;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name:").append(name).append(",");
        sb.append("baseURL:").append(baseURL).append(",");
        sb.append("description:").append(description).append(",");
        sb.append("rssURL:").append(rssURL).append(",");
        return sb.toString();
    }
}
