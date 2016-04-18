package news.caughtup.socket;

public class CaughtUpSocket {
    private Dispatcher dispatcher;

    public CaughtUpSocket(Dispatcher dispatcher, String username) {
        this.dispatcher = dispatcher;
        this.dispatcher.attach(username, this);
        initializeSocket();
    }

    private void initializeSocket() {
        // TODO: initialize input/output streams
    }

    public void emit(SocketMessage message) {
        // TODO: push notification back to client
    }
}
