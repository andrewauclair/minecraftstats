package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.LevelModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;
import org.junit.Before;
import org.junit.Test;
import stats.nbt.model.LevelModel;
import stats.nbt.model.VersionModel;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_String;

public class LevelModelSpecification {
	private LevelModel level;
	private TAG_Compound compound;
	
	private String levelName;
	private long randomSeed;
	private int spawnX;
	private int spawnY;
	private int spawnZ;
	private int version;
	private VersionModel versionCompound;
	private int id;
	private String name;
	private byte snapshot;
	
	@Before
	public void setup() {
		level = new LevelModel();
		compound = new TAG_Compound("");
		levelName = "Minecraft World";
		randomSeed = -583940300333L;
		spawnX = 30;
		spawnY = 64;
		spawnZ = 100;
		version = 19133;
		id = 15;
		name = "Test";
		snapshot = 2;
		versionCompound = new VersionModel();
		versionCompound.setId(id);
		versionCompound.setName(name);
		versionCompound.setSnapshot(snapshot);
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
		TAG_Compound data = new TAG_Compound(dataCompoundName);
		TAG_Compound versionCompound = new TAG_Compound(versionCompoundName);
		versionCompound.addTAG(new TAG_Int(VersionModel.idTagName, id));
		versionCompound.addTAG(new TAG_String(VersionModel.nameTagName, name));
		versionCompound.addTAG(new TAG_Byte(VersionModel.snapshotTagName, snapshot));
		data.addTAG(versionCompound);
		data.addTAG(new TAG_String(levelNameTagName, levelName));
		data.addTAG(new TAG_Long(randomSeedTagName, randomSeed));
		data.addTAG(new TAG_Int(spawnXTagName, spawnX));
		data.addTAG(new TAG_Int(spawnYTagName, spawnY));
		data.addTAG(new TAG_Int(spawnZTagName, spawnZ));
		data.addTAG(new TAG_Int(versionTagName, version));
		
		compound.addTAG(data);
		
		level.readFromCompound(compound);
		
		VersionModel verModel = level.getVersionCompound();
		
		assertEquals(this.versionCompound.getId(), verModel.getId());
		assertEquals(this.versionCompound.getName(), verModel.getName());
		assertEquals(this.versionCompound.getSnapshot(), verModel.getSnapshot());
		assertEquals(levelName, level.getLevelName());
		assertEquals(randomSeed, level.getRandomSeed());
		assertEquals(spawnX, level.getSpawnX());
		assertEquals(spawnY, level.getSpawnY());
		assertEquals(spawnZ, level.getSpawnZ());
		assertEquals(version, level.getVersion());
	}
	
	@Test
	public void ShouldWriteToCompound() {
		level.setLevelName(levelName);
		level.setRandomSeed(randomSeed);
		level.setSpawnX(spawnX);
		level.setSpawnY(spawnY);
		level.setSpawnZ(spawnZ);
		level.setVersion(version);
		level.setVersionCompound(versionCompound);
		
		level.writeToCompound(compound);
		
		TAG_Compound data = (TAG_Compound)compound.getTAG(dataCompoundName);
		TAG_Compound versionCompound = (TAG_Compound)data.getTAG(versionCompoundName);
		
		assertTagInt(id, VersionModel.idTagName, versionCompound);
		assertTagString(name, VersionModel.nameTagName, versionCompound);
		assertTagByte(snapshot, VersionModel.snapshotTagName, versionCompound);
		assertTagString(levelName, levelNameTagName, data);
		assertTagLong(randomSeed, randomSeedTagName, data);
		assertTagInt(spawnX, spawnXTagName, data);
		assertTagInt(spawnY, spawnYTagName, data);
		assertTagInt(spawnZ, spawnZTagName, data);
		assertTagInt(version, versionTagName, data);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		level.readFromCompound(compound);
		
		compound.addTAG(new TAG_Compound(dataCompoundName));
		
		level.readFromCompound(compound);
	}
}
