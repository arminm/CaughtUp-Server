package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/login/{username}")
public class LoginService {
	
	@POST
	@Consumes("*/*")
	public synchronized String login(@PathParam("username") String username) {
		return "Successfully logged in user: " + username;
	}
}
