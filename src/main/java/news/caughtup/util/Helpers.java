package news.caughtup.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import news.caughtup.model.User;

/**
 * @author CaughtUp
 *
 */
public class Helpers {
	private static Gson gson;
	
	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}
	
	/**
	 * Returns an Object by parsing a JSON object received through HTTP
	 * @param req
	 * @param objClass
	 * @return
	 * @throws IOException
	 */
	public static <T> T getObjectFromJSON(HttpServletRequest req, Class<T> objClass) throws IOException {
		Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        return gson.fromJson(sb.toString(), objClass);
	}
	
	/**
	 * Used to create a JSON object containing an error message
	 * @param message
	 * @return
	 */
	public static String getErrorJSON(String message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("error", message);
		return Helpers.getGson().toJson(jsonObject);
	}
	
	/**
	 * Used to create a JSON object containing a generic message
	 * @param message
	 * @return
	 */
	public static String getMessageJSON(String message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", message);
		return Helpers.getGson().toJson(jsonObject);
	}
	
	/**
	 * Used to create a custom JSON object using {"key": "value"}
	 * @param key
	 * @param value
	 * @return
	 */
	public static String getCustomJSON(String key, String value) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(key, value);
		return Helpers.getGson().toJson(jsonObject);
	}
	
	/**
	 * Used to filter out passwords from users returned to the client
	 * @param users
	 */
	public static void filterPassword(List<User> users) {
		for (User user: users) {
			user.setPassword(null);
		}
	}
}
