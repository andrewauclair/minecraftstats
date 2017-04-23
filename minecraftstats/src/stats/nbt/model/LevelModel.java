package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_String;

public class LevelModel {
	public static final String dataCompoundName = "Data";
	public static final String versionCompoundName = "Version";
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
		TAG_Compound data = (TAG_Compound)compound.getTAG(dataCompoundName);
		
		if (data != null) {
			levelName = getStringValue(data, levelNameTagName);
			randomSeed = getLongValue(data, randomSeedTagName);
			spawnX = getIntValue(data, spawnXTagName);
			spawnY = getIntValue(data, spawnYTagName);
			spawnZ = getIntValue(data, spawnZTagName);
			version = getIntValue(data, versionTagName);
		}
	}
	
	public void writeToCompound(TAG_Compound compound) {
		TAG_Compound data = new TAG_Compound(dataCompoundName);
		data.addTAG(new TAG_String(levelNameTagName, levelName));
		data.addTAG(new TAG_Long(randomSeedTagName, randomSeed));
		data.addTAG(new TAG_Int(spawnXTagName, spawnX));
		data.addTAG(new TAG_Int(spawnYTagName, spawnY));
		data.addTAG(new TAG_Int(spawnZTagName, spawnZ));
		data.addTAG(new TAG_Int(versionTagName, version));
		compound.addTAG(data);
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
