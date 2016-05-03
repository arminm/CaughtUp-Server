package news.caughtup.model;


/**
 * @author CaughtUp
 *
 */
public class UploadedImage {
	private String image;
	private String type;
	
	public UploadedImage(String image, String type) {
		super();
		this.image = image;
		this.type = type;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
