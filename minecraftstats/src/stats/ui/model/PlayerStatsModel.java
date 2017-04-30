package stats.ui.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PlayerStatsModel {
	public static final String minutesPlayedStat = "stat.playOneMinute";
	public static final String chestsOpenedStat = "stat.chestOpened";
	public static final String enderChestsOpenedStat = "stat.enderchestOpened";
	public static final String sneakTimeStat = "stat.sneakTime";
	public static final String timeSinceDeathStat = "stat.timeSinceDeath";
	
	private String UUID;
	private long minutesPlayed;
	private long chestsOpened;
	private long enderChestsOpened;
	private long sneakTime;
	private long timeSinceDeath;
	
	public PlayerStatsModel(String UUID) {
		this.UUID = UUID;
	}
	
	public void readFromJson(JsonObject obj) {
		minutesPlayed = getLongProp(obj, minutesPlayedStat);
		chestsOpened = getLongProp(obj, chestsOpenedStat);
		enderChestsOpened = getLongProp(obj, enderChestsOpenedStat);
		sneakTime = getLongProp(obj, sneakTimeStat);
		timeSinceDeath = getLongProp(obj, timeSinceDeathStat);
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
	
	public double getSecondsSneaking() {
		return sneakTime / 20.0;
	}
	
	public double getSecondsSinceDeath() {
		return timeSinceDeath / 20.0;
	}
}
