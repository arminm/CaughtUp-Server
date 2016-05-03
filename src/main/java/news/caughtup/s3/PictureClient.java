package news.caughtup.s3;

import java.io.InputStream;

/**
 * @author CaughtUp
 * Interface which needs to be implemented by different clients to upload images
 */
public interface PictureClient {
    public String uploadPicture(InputStream stream);
    public InputStream downloadPicture();
    public boolean deletePicture(String profilePic);
}
