package news.caughtup.exception;

public enum ExceptionType {
    UsernameTakenException (1, "Username already taken."),
    UserNotFoundException (2, "User not found.");
	public final int errorno;
    public final String errormsg;
    ExceptionType(int errorno, String errormsg) {
        this.errorno = errorno;
        this.errormsg = errormsg;
    }
}