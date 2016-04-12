package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/share/{username}")
public class ShareService {
	
	@GET
	public synchronized String getArticles(@PathParam("username") String username) {
		return "Successfully retrieved articles shared by followers of user: " + username;
	}
	
	@POST
	@Consumes("*/*")
	public synchronized String shareArticles(@PathParam("username") String username, String article) {
		return "Successfully shared article " + article +  " of user: " + username;
	}
}
