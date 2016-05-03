package news.caughtup.model;

/**
 * @author CaughtUp
 * Model for the follower-following relationship
 */
public class Follow {
	private Long userId;
	private Long resourceId;
	
	public Follow(Long userId, Long resourceId) {
		super();
		this.userId = userId;
		this.resourceId = resourceId;
	}
	
	public synchronized Long getUserId() {
		return userId;
	}
	public synchronized void setUserId(Long userId) {
		this.userId = userId;
	}
	public synchronized Long getResourceId() {
		return resourceId;
	}
	public synchronized void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
}
