package stats.spec.ui.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.PlayerDataModel;
import stats.nbt.model.tags.TAG_Compound;
import stats.ui.model.MinecraftSaveData;

public class MinecraftSaveDataSpecification {
	private MinecraftSaveData save;
	
	@Before
	public void setup() {
		save = new MinecraftSaveData();
	}
	
	@Test
	public void ShouldAllowSetAndGetOfRoot() {
		TAG_Compound root = new TAG_Compound("");
		save.setRoot(root);
		
		assertEquals(root, save.getRoot());
	}
	
	@Test
	public void ShouldAddPlayer() {
		PlayerDataModel player = new PlayerDataModel("Test-123");
		
		save.addPlayer(player);
		
		assertEquals(player, save.getPlayer("Test-123"));
	}
	
	@Test
	public void ShouldRemovePlayer() {
		PlayerDataModel player = new PlayerDataModel("Test-123");
		
		save.addPlayer(player);
		
		assertEquals(player, save.getPlayer("Test-123"));
		
		save.removePlayer(player);
		
		assertNull(save.getPlayer("Test-123"));
	}
	
	@Test
	public void ShouldReturnListOfPlayers() {
		PlayerDataModel player1 = new PlayerDataModel("1");
		PlayerDataModel player2 = new PlayerDataModel("2");
		
		save.addPlayer(player1);
		save.addPlayer(player2);
		
		ArrayList<PlayerDataModel> players = save.getPlayers();
		
		assertEquals(2, players.size());
		assertTrue(players.contains(player1));
		assertTrue(players.contains(player2));
	}
}
