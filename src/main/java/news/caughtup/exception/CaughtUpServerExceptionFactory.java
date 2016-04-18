package news.caughtup.exception;

public class CaughtUpServerExceptionFactory {

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
