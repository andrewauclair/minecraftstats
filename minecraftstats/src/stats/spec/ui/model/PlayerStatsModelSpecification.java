package stats.spec.ui.model;

import static org.junit.Assert.*;
import static stats.ui.model.PlayerStatsModel.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import stats.ui.model.PlayerStatsModel;

public class PlayerStatsModelSpecification {
	private static final String UUID = "Test-123";
	PlayerStatsModel stats;
	
	@Before
	public void setup() {
		stats = new PlayerStatsModel(UUID);
	}
	
	@Test
	public void ShouldCreateObjectWithUUID() {
		assertEquals(UUID, stats.getUUID());
	}
	
	@Test
	public void ShouldSetStatPropertyNames() {
		assertEquals("stat.playOneMinute", minutesPlayedStat);
		assertEquals("stat.chestOpened", chestsOpenedStat);
		assertEquals("stat.enderchestOpened", enderChestsOpenedStat);
		assertEquals("stat.sneakTime", sneakTimeStat);
		assertEquals("stat.timeSinceDeath", timeSinceDeathStat);
	}
	
	@Test
	public void ShouldParsePlayerOneMinute() {
		stats.readFromJson(createJson(minutesPlayedStat, 1310));
		
		assertEquals(65.5, stats.getSecondsPlayed(), 0.001);
	}
	
	@Test
	public void ShouldParseChestOpened() {
		stats.readFromJson(createJson(chestsOpenedStat, 5));
		
		assertEquals(5, stats.getChestsOpened());
	}
	
	@Test
	public void ShouldParseEnderChestsOpened() {
		stats.readFromJson(createJson(enderChestsOpenedStat, 5));
		
		assertEquals(5, stats.getEnderChestsOpened());
	}
	
	@Test
	public void ShouldParseSneakTime() {
		stats.readFromJson(createJson(sneakTimeStat, 50));
		
		assertEquals(2.5, stats.getSecondsSneaking(), 0.001);
	}
	
	@Test
	public void ShouldParseTimeSinceDeath() {
		stats.readFromJson(createJson(timeSinceDeathStat, 50));
		
		assertEquals(2.5, stats.getSecondsSinceDeath(), 0.001);
	}
	
	private JsonObject createJson(String name, long value) {
		String json = "{ \"" + name + "\": " + Long.toString(value) + " }";
		return new JsonParser().parse(json).getAsJsonObject();
	}
}
