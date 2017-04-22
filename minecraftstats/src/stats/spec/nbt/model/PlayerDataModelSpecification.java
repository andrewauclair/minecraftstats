package stats.spec.nbt.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.PlayerDataModel;

public class PlayerDataModelSpecification {

	private String UUID = "1a0857f7-b10c-457b-b136-9643b4f26ab4";
	private String userName = "Player1";
	private PlayerDataModel playerData;
	
	@Before
	public void setup() {
		playerData = new PlayerDataModel(userName, UUID);
	}
	
	@Test
	public void ShouldCreateObjectWithUUIDAndUsername() {
		assertEquals(UUID, playerData.getUUID());
		assertEquals(userName, playerData.getUserName());
	}
	
	@Test
	public void ShouldReadFromCompound() {
		
		// TODO I think I need to build a test utility class that can write input and output streams
		// for nbt tags to use in tests like these
		
		assertTrue(false);
	}
	
	@Test
	public void ShouldWriteToCompound() {
		assertTrue(false);
	}
}
