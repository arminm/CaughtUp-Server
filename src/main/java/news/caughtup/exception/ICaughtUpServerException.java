package news.caughtup.exception;

public interface ICaughtUpServerException {
    public void fix(int errorno);
    public void log();
}
