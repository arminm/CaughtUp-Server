package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import news.caughtup.model.SharedArticle;

@Path("/share")
public class ShareService {
	
	@GET
	public synchronized String getSharedArticles(@QueryParam("username") String username) {
		return "Successfully retrieved articles shared by followers of user: " + username;
	}
	
	@POST
	@Consumes("*/*")
	public synchronized String shareArticles(SharedArticle article) {
		return "Successfully shared article " + article.getArticle() +  " of user: " + article.getUsername();
	}
}
