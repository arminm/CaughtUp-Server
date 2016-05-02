package news.caughtup.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import news.caughtup.model.User;

public class Helpers {
	private static Gson gson;
	public static <T> T getObjectFromJSON(HttpServletRequest req, Class<T> objClass) throws IOException {
		Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = req.getReader().readLine()) != null) {
            sb.append(s);
        }
        return gson.fromJson(sb.toString(), objClass);
	}
	
	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}
	
	public static String getErrorJSON(String message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("error", message);
		return Helpers.getGson().toJson(jsonObject);
	}
	
	public static String getMessageJSON(String message) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", message);
		return Helpers.getGson().toJson(jsonObject);
	}
	
	public static void filterPassword(List<User> users) {
		for (User user: users) {
			user.setPassword(null);
		}
	}
}
