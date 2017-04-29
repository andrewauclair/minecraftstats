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
		assertEquals("stat.playOneMinute", minutesPlayedProp);
		assertEquals("stat.chestOpened", chestsOpenedProp);
		assertEquals("stat.enderchestOpened", enderChestsOpenedProp);
	}
	
	@Test
	public void ShouldParsePlayerOneMinute() {
		stats.readFromJson(createJson(minutesPlayedProp, 1310));
		
		assertEquals(65.5, stats.getSecondsPlayed(), 0.001);
	}
	
	@Test
	public void ShouldParseChestOpened() {
		stats.readFromJson(createJson(chestsOpenedProp, 5));
		
		assertEquals(5, stats.getChestsOpened());
	}
	
	@Test
	public void ShouldParseEnderChestsOpened() {
		stats.readFromJson(createJson(enderChestsOpenedProp, 5));
		
		assertEquals(5, stats.getEnderChestsOpened());
	}
	
	private JsonObject createJson(String name, long value) {
		String json = "{ \"" + name + "\": " + Long.toString(value) + " }";
		return new JsonParser().parse(json).getAsJsonObject();
	}
}
