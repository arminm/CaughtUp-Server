package news.caughtup.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import news.caughtup.database.FollowerDBAdapter;
import news.caughtup.database.UserDBAdapter;
import news.caughtup.exception.CaughtUpServerException;
import news.caughtup.model.UploadedImage;
import news.caughtup.model.User;
import news.caughtup.s3.S3Proxy;
import news.caughtup.util.Constants;
import news.caughtup.util.Helpers;

/**
 * @author CaughtUp
 *
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * [GET] /profile/:username
	 * Used to retrieve the info of a user
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
		String username = substrings[substrings.length - 1];

		User existingUser = null;
		try {
			// Get user from DB
			existingUser = UserDBAdapter.getUser(username);

			HashMap<String, Object> results = new HashMap<String, Object>();
			existingUser.setPassword(null);
			results.put("profile", existingUser);
			// Get the users followers
			ArrayList<User> followers = FollowerDBAdapter.getFollowers(existingUser.getResourceId());
			Helpers.filterPassword(followers);
			for (User user: followers) {
				user.setPassword(null);
			}
			results.put("followers", followers);
			// Send JSON response back to the client
			out.println(Helpers.getGson().toJson(results));

		} catch (SQLException e) {
			System.err.println("Failed to get user with username: " + username);
			System.err.println(e);
			resp.setStatus(500);
			out.println(Helpers.getErrorJSON("Internal Error."));
		} catch (CaughtUpServerException e) {
			out.println(e.fix(resp));
		}
	}

	/**
	 * [PUT] /profile/:username?picture={true,false}
	 * Used to update the information of a user
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prepare to send JSON response back to the client
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String[] substrings = req.getRequestURI().substring(req.getContextPath().length()).split("/");
		String username = substrings[substrings.length - 1];
		String isPicture = req.getParameter("picture");
		User existingUser = null;
		// If it's an image update
		if (isPicture.equals("true")) {
			// Get the encoded image from the json string
			UploadedImage image = (UploadedImage) Helpers.getObjectFromJSON(req, UploadedImage.class);
			byte[] byteArray = Base64.decodeBase64(image.getImage());
			InputStream stream = new ByteArrayInputStream(byteArray);
			String imageType = image.getType();
			try {
				// Get user info from DB
				existingUser = UserDBAdapter.getUser(username);
				S3Proxy proxy = new S3Proxy(Constants.S3_PROFILE_PICTURE_PATH + username, 
						Constants.PROFILE_PICTURE_NAME + "." + imageType);

				// If a profile_picture_url exists, delete the old picture first
				// S3 limitation, since we can't just upload a file with the same name 
				String oldProfilePic = existingUser.getProfilePictureURL();
				if (oldProfilePic != null) {
					String[] parts = oldProfilePic.split("/");
					String profilePicName = parts[parts.length - 1];
					if (!proxy.deletePicture(profilePicName)) {
						resp.setStatus(500);
						out.println(Helpers.getErrorJSON("Image file could not be properly updated."));
						return;
					}
				}
				// Upload new image
				String pictureURL = proxy.uploadPicture(stream);
				// Store the new picture url
				UserDBAdapter.updateUserProfilePic(username, pictureURL);
				out.println(Helpers.getCustomJSON("profile_picture_url", pictureURL));
			} catch (SQLException e) {
				System.err.println("Failed to update profile image for user:" + existingUser.toString());
				System.err.println(e);
				resp.setStatus(500);
				out.println(Helpers.getErrorJSON("Internal Error."));
			} catch (CaughtUpServerException e) {
				resp.setStatus(404);
				e.printStackTrace();
			}
		} else {
			// It's an update without image
			User user = (User) Helpers.getObjectFromJSON(req, User.class);
			user.setUsername(username);
			// Update the info of a user in the DB
			try {
				UserDBAdapter.updateUser(user);
				out.println(Helpers.getMessageJSON("Success"));
			} catch (SQLException e) {
				System.err.println("Failed to update user info in DB:" + user.toString());
				System.err.println(e);
				resp.setStatus(500);
				out.println(Helpers.getErrorJSON("Internal Error."));
			}  
		}
	}
}