package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.LevelModel.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.LevelModel;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_String;

public class LevelModelSpecification {

	private LevelModel level;
	
	private String levelName;
	private long randomSeed;
	private int spawnX;
	private int spawnY;
	private int spawnZ;
	private int version;
	
	@Before
	public void setup() {
		level = new LevelModel();
		levelName = "Minecraft World";
		randomSeed = -583940300333L;
		spawnX = 30;
		spawnY = 64;
		spawnZ = 100;
		version = 19133;
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("LevelName", levelNameTagName);
		assertEquals("RandomSeed", LevelModel.randomSeedTagName);
		assertEquals("SpawnX", LevelModel.spawnXTagName);
		assertEquals("SpawnY", LevelModel.spawnYTagName);
		assertEquals("SpawnZ", LevelModel.spawnZTagName);
		assertEquals("version", LevelModel.versionTagName);
	}
	
	@Test
	public void ShouldReadFromCompound() {
		TAG_Compound compound = new TAG_Compound("");
		compound.addTAG(new TAG_String(levelNameTagName, levelName));
		compound.addTAG(new TAG_Long(randomSeedTagName, randomSeed));
		compound.addTAG(new TAG_Int(spawnXTagName, spawnX));
		compound.addTAG(new TAG_Int(spawnYTagName, spawnY));
		compound.addTAG(new TAG_Int(spawnZTagName, spawnZ));
		compound.addTAG(new TAG_Int(versionTagName, version));
		
		level.readFromCompound(compound);
		
		assertEquals(levelName, level.getLevelName());
		assertEquals(randomSeed, level.getRandomSeed());
		assertEquals(spawnX, level.getSpawnX());
		assertEquals(spawnY, level.getSpawnY());
		assertEquals(spawnZ, level.getSpawnZ());
		assertEquals(version, level.getVersion());
	}
	
	@Test
	public void ShouldWriteToCompound() {
		TAG_Compound compound = new TAG_Compound("");
		
		level.setLevelName(levelName);
		level.setRandomSeed(randomSeed);
		level.setSpawnX(spawnX);
		level.setSpawnY(spawnY);
		level.setSpawnZ(spawnZ);
		level.setVersion(version);
		
		level.writeToCompound(compound);
		
		assertTrue(compound.hasTAG("version"));
		assertEquals(levelName, ((TAG_String)compound.getTAG(levelNameTagName)).getValue());
		assertEquals(randomSeed, ((TAG_Long)compound.getTAG(randomSeedTagName)).getValue());
		assertEquals(spawnX, ((TAG_Int)compound.getTAG(spawnXTagName)).getValue());
		assertEquals(spawnY, ((TAG_Int)compound.getTAG(spawnYTagName)).getValue());
		assertEquals(spawnZ, ((TAG_Int)compound.getTAG(spawnZTagName)).getValue());
		assertEquals(version, ((TAG_Int)compound.getTAG(versionTagName)).getValue());
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		TAG_Compound compound = new TAG_Compound("");
		
		level.readFromCompound(compound);
	}
}
