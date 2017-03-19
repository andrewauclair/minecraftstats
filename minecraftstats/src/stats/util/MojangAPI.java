package stats.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class MojangAPI {

	public static String getUserName(String UUID) {
		
		String urlString = "https://sessionserver.mojang.com/session/minecraft/profile/" + UUID;
		
		try {
			URL url = new URL(urlString);
			
			URLConnection conn = url.openConnection();
			
			InputStream is = conn.getInputStream();
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			String data = new String(bytes, "UTF-8");
			
			JsonObject jsonObject = null;
			
			try {
				jsonObject = new JsonParser().parse(data).getAsJsonObject();
				return jsonObject.get("name").getAsString();
			}
			catch (IllegalStateException e) {
				System.out.println("Error fetching: " + UUID);
				e.printStackTrace();
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
			// failed to fetch the name
		}
		
		return "";
	}
}
