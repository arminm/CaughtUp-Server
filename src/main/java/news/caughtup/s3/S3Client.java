package news.caughtup.s3;

import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * @author CaughtUp
 * S3 specific client for uploading images
 */
public class S3Client implements PictureClient {
    private static final AmazonS3Client s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
    private String bucketName;
    private String pictureName;

    public S3Client(String bucketName, String pictureName) {
        this.bucketName = bucketName;
        this.pictureName = pictureName;
        s3Client.setRegion(Region.getRegion(Regions.US_WEST_1));
    }

    public String getPictureURL() {
        return pictureName;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureName = pictureURL;
    }

    /**
     * Method to upload an image to S3
     */
    @Override
    public String uploadPicture(InputStream stream) {
        try {
        	// Upload the image with public read access, so that clients can download it
            s3Client.putObject(new PutObjectRequest(bucketName, pictureName, stream, new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return s3Client.getResourceUrl(bucketName, pictureName);
         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return null;
    }

    /**
     * Not used after all, since images are downloaded by clients directly
     */
    @Override
    public InputStream downloadPicture() {
        try {
            GetObjectRequest request = new GetObjectRequest(bucketName,
                    pictureName);
            S3Object object = s3Client.getObject(request);
            return object.getObjectContent();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return null;
    }

    /**
     * Method to delete an image in S3
     * Required to clear out old profile pictures
     */
    @Override
    public boolean deletePicture(String profilePic) {
        try {
            s3Client.deleteObject(bucketName, profilePic);
            return true;
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return false;
    }
}
