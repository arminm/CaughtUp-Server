package news.caughtup.util;


/**
 * Not used in the current implementation
 *
 */
public class CaughtUpServerLogger {
    private static CaughtUpServerLogger logger;

    private CaughtUpServerLogger() {}

    public static CaughtUpServerLogger getLogger() {
        if (logger == null) {
            logger = new CaughtUpServerLogger();
        }
        return logger;
    }

    public void log(String message) {
        // TODO: Add logging file and write message
    }
}
