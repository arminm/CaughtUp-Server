package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/profile/{username}")
public class ProfileService {
	
	@GET
	@Consumes("*/*")
	public synchronized String getProfile(@PathParam("username") String username) {
		return "Successfully got profile of user: " + username;
	}
	
	@PUT
	@Consumes("*/*")
	public synchronized String updateProfile(@PathParam("username") String username,
											 @QueryParam("picture") boolean isPicture) {
		if (isPicture) {
			return "Successfully updated profile picture of user: " + username;
		} else {
			return "Successfully updated profile info of user: " + username;
		}
	}
}
