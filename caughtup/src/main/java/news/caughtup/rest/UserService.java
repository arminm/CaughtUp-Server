package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class UserService {
	
	@Path("register/{username}")
	@POST
	@Consumes("*/*")
	public synchronized String register(@PathParam("username") String username) {
		return "Successfully registered user: " + username;
	}
	
	@Path("login/{username}")
	@POST
	@Consumes("*/*")
	public synchronized String login(@PathParam("username") String username) {
		return "Successfully logged in user: " + username;
	}
	
	@Path("logout/{username}")
	@GET
	@Consumes("*/*")
	public synchronized String logout(@PathParam("username") String username) {
		return "Successfully logged out user: " + username;
	}
}
