package news.caughtup.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/articles")
public class ArticleService {

	@GET
	public synchronized String getArticles(@QueryParam("source") String source) {
		return "Successfully retrieved articles for source: " + source;
	}
}
