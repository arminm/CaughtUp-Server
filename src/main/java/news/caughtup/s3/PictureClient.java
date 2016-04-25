package news.caughtup.s3;

import java.io.InputStream;

public interface PictureClient {
    public String uploadPicture(InputStream stream);
    public InputStream downloadPicture();
    public boolean deletePicture();
}
