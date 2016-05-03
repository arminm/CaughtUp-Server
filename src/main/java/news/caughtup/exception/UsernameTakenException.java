package news.caughtup.exception;

import javax.servlet.http.HttpServletResponse;

import news.caughtup.util.Helpers;

public class UsernameTakenException extends CaughtUpServerException {
    private static final long serialVersionUID = 1L;

    @Override
    public String fix(HttpServletResponse resp) {
    	resp.setStatus(403);
    	return Helpers.getErrorJSON(ExceptionType.UsernameTakenException.errormsg);
    }

    @Override
    public void log() {
        System.out.println("Username is taken.");
    }

}
