package news.caughtup.exception;

import javax.servlet.http.HttpServletResponse;

public interface ICaughtUpServerException {
    public String fix(HttpServletResponse response);
    public void log();
}
