package stats.nbt.model;

import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_String;

public class LevelModel {
	public static final String levelNameTagName = "LevelName";
	public static final String randomSeedTagName = "RandomSeed";
	public static final String spawnXTagName = "SpawnX";
	public static final String spawnYTagName = "SpawnY";
	public static final String spawnZTagName = "SpawnZ";
	public static final String versionTagName = "version";
	
	private String levelName;
	private long randomSeed;
	private int spawnX;
	private int spawnY;
	private int spawnZ;
	private int version;
	
	public void readFromCompound(TAG_Compound compound) {
		TAG_String tagLevelName = (TAG_String)compound.getTAG(levelNameTagName);
		TAG_Long tagRandomSeed = (TAG_Long)compound.getTAG(randomSeedTagName);
		spawnX = readInt(compound, spawnXTagName);
		spawnY = readInt(compound, spawnYTagName);
		spawnZ = readInt(compound, spawnZTagName);
		version = readInt(compound, versionTagName);
		
		if (tagLevelName != null) {
			levelName = tagLevelName.getValue();
		}
		
		if (tagRandomSeed != null) {
			randomSeed = tagRandomSeed.getValue();
		}
	}
	
	private int readInt(TAG_Compound compound, String name) {
		TAG_Int tag = (TAG_Int)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return 0;
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_String(levelNameTagName, levelName));
		compound.addTAG(new TAG_Long(randomSeedTagName, randomSeed));
		compound.addTAG(new TAG_Int(spawnXTagName, spawnX));
		compound.addTAG(new TAG_Int(spawnYTagName, spawnY));
		compound.addTAG(new TAG_Int(spawnZTagName, spawnZ));
		compound.addTAG(new TAG_Int(versionTagName, version));
	}
	
	public String getLevelName() {
		return levelName;
	}
	
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public long getRandomSeed() {
		return randomSeed;
	}
	
	public void setRandomSeed(long randomSeed) {
		this.randomSeed = randomSeed;
	}
	
	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public int getSpawnZ() {
		return spawnZ;
	}

	public void setSpawnZ(int spawnZ) {
		this.spawnZ = spawnZ;
	}

	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
}
