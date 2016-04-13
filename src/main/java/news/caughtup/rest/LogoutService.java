package news.caughtup.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("logout/{username}")
public class LogoutService {
	
	@GET
	public synchronized String logout(@PathParam("username") String username) {
		return "Successfully logged out user: " + username;
	}
}
