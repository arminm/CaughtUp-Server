package news.caughtup.exception;

import javax.servlet.http.HttpServletResponse;

import news.caughtup.util.Helpers;

public class UserNotFoundException extends CaughtUpServerException {
    private static final long serialVersionUID = 1L;

    @Override
    public String fix(HttpServletResponse resp) {
    	resp.setStatus(404);
    	return Helpers.getErrorJSON(ExceptionType.UserNotFoundException.errormsg);
    }

    @Override
    public void log() {
        System.out.println("User was not found.");
    }

}
