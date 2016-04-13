package news.caughtup.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/search/")
public class SearchService {
	
	@GET
	public synchronized String searchKeyword(@QueryParam("keyword") String keyword) {
		return "Successfully retrieved results for keyword: " + keyword;
	}
}
