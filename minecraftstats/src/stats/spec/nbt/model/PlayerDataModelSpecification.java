package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.PlayerDataModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.PlayerDataModel;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int;

public class PlayerDataModelSpecification {

	private String UUID = "1a0857f7-b10c-457b-b136-9643b4f26ab4";
	private PlayerDataModel playerData;
	private TAG_Compound compound;
	private int dimension;
	private float health;
	private int playerGameType;
	private int xpLevel;
	
	@Before
	public void setup() {
		playerData = new PlayerDataModel(UUID);
		compound = new TAG_Compound("");
		dimension = 4;
		health = 4.88f;
		playerGameType = 1;
		xpLevel = 89;
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("Dimension", dimensionTagName);
		assertEquals("Health", healthTagName);
		assertEquals("playerGameType", playerGameTypeTagName);
		assertEquals("XpLevel", xpLevelTagName);
	}
	
	@Test
	public void ShouldCreateObjectWithUUID() {
		assertEquals(UUID, playerData.getUUID());
	}
	
	@Test
	public void ShouldReadFromCompound() {
		compound.addTAG(new TAG_Int(dimensionTagName, dimension));
		compound.addTAG(new TAG_Float(healthTagName, health));
		compound.addTAG(new TAG_Int(playerGameTypeTagName, playerGameType));
		compound.addTAG(new TAG_Int(xpLevelTagName, xpLevel));
		
		playerData.readFromCompound(compound);
		
		assertEquals(dimension, playerData.getDimension());
		assertEquals(health, playerData.getHealth(), 0.001);
		assertEquals(playerGameType, playerData.getPlayerGameType());
		assertEquals(xpLevel, playerData.getXpLevel());
	}
	
	@Test
	public void ShouldWriteToCompound() {
		playerData.setDimension(dimension);
		playerData.setHealth(health);
		playerData.setPlayerGameType(playerGameType);
		playerData.setXpLevel(xpLevel);
		
		playerData.writeToCompound(compound);
		
		assertTagInt(dimension, dimensionTagName, compound);
		assertTagFloat(health, healthTagName, compound);
		assertTagInt(playerGameType, playerGameTypeTagName, compound);
		assertTagInt(xpLevel, xpLevelTagName, compound);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		playerData.readFromCompound(compound);
	}
}
