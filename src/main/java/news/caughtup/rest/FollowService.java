package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/follow/{username}")
public class FollowService {
	
	@GET
	public synchronized String getFollowers(@PathParam("username") String username,
											@QueryParam("type") String type) {
		if (type.equals("users")) {
			return "Successfully retrieved followers of user: " + username;
		} else if (type.equals("sources")) {
			return "Successfully retrieved sources followed by user: " + username;
		} else {
			return "Unhandled type of followed objects";
		}
	}
	
	@POST
	@Consumes("*/*")
	public synchronized String addFollower(@PathParam("username") String username, String follower) {
		return "Successfully added follower " + follower + " for user: " + username;
	}
	
	@DELETE
	@Consumes("*/*")
	public synchronized String removeFollower(@PathParam("username") String username, String follower) {
		return "Successfully deleted follower " + follower +  " for user: " + username;
	}
}
