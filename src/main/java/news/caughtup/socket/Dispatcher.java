package news.caughtup.socket;

import java.util.HashMap;
import java.util.Map;

/**
 * Not used in the current implementation
 *
 */
public class Dispatcher {
    private Map<String, CaughtUpSocket> caughtUpSockets;
    private static Dispatcher dispatcher;
    private SocketMessage socketMessage;

    private Dispatcher() {}

    public Dispatcher getDispatcher() {
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
            caughtUpSockets = new HashMap<String, CaughtUpSocket>();
        }
        return dispatcher;
    }

    public SocketMessage getSocketMessage() {
        return socketMessage;
    }

    public void setSocketMessage(SocketMessage socketMessage) {
        this.socketMessage = socketMessage;
    }

    public void attach(String username, CaughtUpSocket socket) {
        if (!caughtUpSockets.containsKey(username)) {
            caughtUpSockets.put(username, socket);
        }
    }

    public void broadcast() {
        for (String key: caughtUpSockets.keySet()) {
            caughtUpSockets.get(key).emit(socketMessage);
        }
    }
}
