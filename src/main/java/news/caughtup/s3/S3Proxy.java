package news.caughtup.s3;

import java.io.InputStream;

/**
 * @author CaughtUp
 * Proxy class to use the S3Client, it delegates the methods to be executed by the client
 */
public class S3Proxy implements PictureClient {
    private S3Client s3Client;

    public S3Proxy(String bucketName, String pictureName) {
        s3Client = new S3Client(bucketName, pictureName);
    }

    @Override
    public String uploadPicture(InputStream stream) {
        return s3Client.uploadPicture(stream);
    }

    @Override
    public InputStream downloadPicture() {
        return s3Client.downloadPicture();
    }

    @Override
    public boolean deletePicture(String profilePic) {
        return s3Client.deletePicture(profilePic);
    }	
}
