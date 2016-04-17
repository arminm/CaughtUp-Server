package news.caughtup.s3;

public class S3Proxy implements PictureClient {
	private S3Client s3Client;
	private String pictureURL;
	
	public S3Proxy(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	
	@Override
	public boolean uploadPicture() {
		if (s3Client == null) {
			s3Client = new S3Client(pictureURL);
		}
		return s3Client.uploadPicture();
	}	
}
