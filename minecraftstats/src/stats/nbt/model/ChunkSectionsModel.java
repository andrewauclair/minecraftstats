package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_Long;

public class ChunkSectionsModel {
	public static final String levelTagName = "Level";
	public static final String inhabitedTimeTagName = "InhabitedTime";
	public static final String lastUpdateTagName = "LastUpdate";
	public static final String lightPopulatedTagName = "LightPopulated";
	public static final String terrainPopulatedTagName = "TerrainPopulated";
	public static final String xPosTagName = "xPos";
	public static final String zPosTagName = "zPos";
	public static final String biomesTagName = "Biomes";
	public static final String heightMapTagName = "HeightMap";
	public static final String dataVersionTagName = "DataVersion";
	
	private long inhabitedTime;
	private long lastUpdate;
	private byte lightPopulated;
	private byte terrainPopulated;
	private int xPos;
	private int zPos;
	private byte[] biomes;
	private int[] heightMap;
	private int dataVersion;
	
	public void readFromCompound(TAG_Compound compound) {
		TAG_Compound level = (TAG_Compound)compound.getTAG(levelTagName);
		if (level != null) {
			inhabitedTime = getLongValue(level, inhabitedTimeTagName);
			lastUpdate = getLongValue(level, lastUpdateTagName);
			lightPopulated = getByteValue(level, lightPopulatedTagName);
			terrainPopulated = getByteValue(level, terrainPopulatedTagName);
			xPos = getIntValue(level, xPosTagName);
			zPos = getIntValue(level, zPosTagName);
			biomes = getByteArrayValue(level, biomesTagName);
			heightMap = getIntArrayValue(level, heightMapTagName);
		}
		dataVersion = getIntValue(compound, dataVersionTagName);
	}
	
	public void writeToCompound(TAG_Compound compound) {
		TAG_Compound level = new TAG_Compound(levelTagName);
		level.addTAG(new TAG_Long(inhabitedTimeTagName, inhabitedTime));
		level.addTAG(new TAG_Long(lastUpdateTagName, lastUpdate));
		level.addTAG(new TAG_Byte(lightPopulatedTagName, lightPopulated));
		level.addTAG(new TAG_Byte(terrainPopulatedTagName, terrainPopulated));
		level.addTAG(new TAG_Int(xPosTagName, xPos));
		level.addTAG(new TAG_Int(zPosTagName, zPos));
		level.addTAG(new TAG_Byte_Array(biomesTagName, biomes));
		level.addTAG(new TAG_Int_Array(heightMapTagName, heightMap));
		compound.addTAG(level);
		compound.addTAG(new TAG_Int(dataVersionTagName, dataVersion));
	}

	public long getInhabitedTime() {
		return inhabitedTime;
	}

	public void setInhabitedTime(long inhabitedTime) {
		this.inhabitedTime = inhabitedTime;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public byte getLightPopulated() {
		return lightPopulated;
	}

	public void setLightPopulated(byte lightPopulated) {
		this.lightPopulated = lightPopulated;
	}

	public byte getTerrainPopulated() {
		return terrainPopulated;
	}

	public void setTerrainPopulated(byte terrainPopulated) {
		this.terrainPopulated = terrainPopulated;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getzPos() {
		return zPos;
	}

	public void setzPos(int zPos) {
		this.zPos = zPos;
	}

	public byte[] getBiomes() {
		return biomes;
	}

	public void setBiomes(byte[] biomes) {
		this.biomes = biomes;
	}

	public int[] getHeightMap() {
		return heightMap;
	}

	public void setHeightMap(int[] heightMap) {
		this.heightMap = heightMap;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(int dataVersion) {
		this.dataVersion = dataVersion;
	}
}
