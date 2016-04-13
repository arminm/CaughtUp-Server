package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("register/{username}")
public class RegisterService {
	
	@POST
	@Consumes("*/*")
	public synchronized String register(@PathParam("username") String username) {
		return "Successfully registered user: " + username;
	}
}
