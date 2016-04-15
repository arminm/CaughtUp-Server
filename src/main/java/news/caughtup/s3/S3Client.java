package news.caughtup.s3;

public class S3Client implements PictureClient {
	private String pictureURL;
	
	public S3Client(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	@Override
	public boolean uploadPicture() {
		// TODO Auto-generated method stub
		return false;
	}
}
