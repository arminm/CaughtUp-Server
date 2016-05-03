package news.caughtup.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * @author CaughtUp
 * Interface to be implemented by the exceptions
 */
public interface ICaughtUpServerException {
	
    /**
     * Method to fix the exception that was caught
     * @param response
     * @return
     */
    public String fix(HttpServletResponse response);
    
    /**
     * Method to log the exception message
     */
    public void log();
}
