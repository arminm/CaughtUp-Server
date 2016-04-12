package news.caughtup.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
public class Login {
	
	@POST
	@Consumes("*/*")
	public synchronized String login() {
		return "Successfully logged in";
	}
}
