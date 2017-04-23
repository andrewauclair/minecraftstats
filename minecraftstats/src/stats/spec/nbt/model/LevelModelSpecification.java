package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.LevelModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.rules.*;

import stats.nbt.model.LevelModel;
import stats.nbt.model.VersionModel;
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
		assertEquals("Data", dataCompoundName);
		assertEquals("Version", versionCompoundName);
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
		TAG_Compound data = new TAG_Compound(dataCompoundName);
		TAG_Compound versionCompound = new TAG_Compound(versionCompoundName);
		data.addTAG(versionCompound);
		data.addTAG(new TAG_String(levelNameTagName, levelName));
		data.addTAG(new TAG_Long(randomSeedTagName, randomSeed));
		data.addTAG(new TAG_Int(spawnXTagName, spawnX));
		data.addTAG(new TAG_Int(spawnYTagName, spawnY));
		data.addTAG(new TAG_Int(spawnZTagName, spawnZ));
		data.addTAG(new TAG_Int(versionTagName, version));
		
		compound.addTAG(data);
		
		VersionModel mockVersion = Mockito.mock(VersionModel.class);
		
		level.setVersionCompound(mockVersion);
		
		level.readFromCompound(compound);
		
		verify(mockVersion).readFromCompound(versionCompound);
		
		assertEquals(level.getVersionCompound(), mockVersion);
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
		
		VersionModel mockVersion = Mockito.mock(VersionModel.class);
		
		level.setVersionCompound(mockVersion);
		
		level.writeToCompound(compound);
		
		TAG_Compound data = (TAG_Compound)compound.getTAG(dataCompoundName);
		TAG_Compound versionCompound = (TAG_Compound)data.getTAG(versionCompoundName);
		
		//assertTrue(compound.hasTAG("version"));
		verify(mockVersion).writeToCompound(versionCompound);
		assertTagString(levelName, levelNameTagName, data);
		assertTagLong(randomSeed, randomSeedTagName, data);
		assertTagInt(spawnX, spawnXTagName, data);
		assertTagInt(spawnY, spawnYTagName, data);
		assertTagInt(spawnZ, spawnZTagName, data);
		assertTagInt(version, versionTagName, data);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		TAG_Compound compound = new TAG_Compound("");
		
		level.readFromCompound(compound);
		
		compound.addTAG(new TAG_Compound(dataCompoundName));
		
		level.readFromCompound(compound);
	}
}
