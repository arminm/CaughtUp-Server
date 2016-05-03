package news.caughtup.exception;

/**
 * @author CaughtUp
 *
 */
public class CaughtUpServerExceptionFactory {

    /**
     * Factory to return the correct exception type
     * @param type
     * @return
     */
    public static CaughtUpServerException createException(ExceptionType type) {
        switch (type) {
        case UsernameTakenException: 
            return new UsernameTakenException();
        case UserNotFoundException:
            return new UserNotFoundException();
        default: 
            return null;
        }
    }
}
