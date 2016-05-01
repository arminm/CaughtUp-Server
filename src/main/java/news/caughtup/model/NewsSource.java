package news.caughtup.model;

import java.sql.Timestamp;
import java.util.HashMap;

public class NewsSource {
    private Long resourceId;
    private String name;
    private String baseURL;
    private String description;
    private String rssURL;
    private Timestamp latestArticleDate;
    
    public NewsSource(Long resourceId, String name, String baseURL, String description, String rssURL, Timestamp latestArticleDate) {
        super();
        this.resourceId = resourceId;
        this.name = name;
        this.baseURL = baseURL;
        this.description = description;
        this.rssURL = rssURL;
        this.latestArticleDate = latestArticleDate;
    }
    
    public NewsSource(HashMap<String, Object> newsSourceData) {
    	if (newsSourceData == null || newsSourceData.size() == 0) {
    		return;
    	}
    	this.resourceId = (Long) newsSourceData.get("resource_id");
        this.name = (String) newsSourceData.get("name");
        this.baseURL = (String) newsSourceData.get("base_url");
        this.description = (String) newsSourceData.get("description");
        this.rssURL = (String) newsSourceData.get("rss_url");
        this.latestArticleDate = (Timestamp) newsSourceData.get("latest_article_date");
    }
    
    public Long getResourceId() {
        return resourceId == null ? 0 : resourceId;
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

    public Timestamp getLatestArticleDate() {
        return latestArticleDate;
    }

    public void setLatestArticleDate(Timestamp latestArticleDate) {
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
