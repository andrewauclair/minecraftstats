package stats.ui.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PlayerStatsModel {
	public static final String minutesPlayedProp = "stat.playOneMinute";
	public static final String chestsOpenedProp = "stat.chestOpened";
	public static final String enderChestsOpenedProp = "stat.enderchestOpened";
	
	private String UUID;
	private long minutesPlayed;
	private long chestsOpened;
	private long enderChestsOpened;
	
	public PlayerStatsModel(String UUID) {
		this.UUID = UUID;
	}
	
	public void readFromJson(JsonObject obj) {
		minutesPlayed = getLongProp(obj, minutesPlayedProp);
		chestsOpened = getLongProp(obj, chestsOpenedProp);
		enderChestsOpened = getLongProp(obj, enderChestsOpenedProp);
	}
	
	private long getLongProp(JsonObject obj, String prop) {
		JsonElement element = obj.get(prop);
		if (element != null) {
			return element.getAsLong();
		}
		return 0L;
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public double getSecondsPlayed() {
		return minutesPlayed / 20.0;
	}
	
	public long getChestsOpened() {
		return chestsOpened;
	}
	
	public long getEnderChestsOpened() {
		return enderChestsOpened;
	}
}
