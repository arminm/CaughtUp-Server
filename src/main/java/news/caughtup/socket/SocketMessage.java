package news.caughtup.socket;

public class SocketMessage {
	private String type;
	private int resourceID;
	
	public SocketMessage(String type, int resourceID) {
		this.type = type;
		this.resourceID = resourceID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getResourceID() {
		return resourceID;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}
}
