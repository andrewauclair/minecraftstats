package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_Long;

public class ChunkSectionsModel {
	public static final String inhabitedTimeTagName = "InhabitedTime";
	public static final String lastUpdateTagName = "LastUpdate";
	public static final String lightPopulatedTagName = "LightPopulated";
	public static final String terrainPopulatedTagName = "TerrainPopulated";
	public static final String xPosTagName = "xPos";
	public static final String zPosTagName = "zPos";
	public static final String biomesTagName = "Biomes";
	public static final String heightMapTagName = "HeightMap";
	
	private long inhabitedTime;
	private long lastUpdate;
	private byte lightPopulated;
	private byte terrainPopulated;
	private int xPos;
	private int zPos;
	private byte[] biomes;
	private int[] heightMap;
	
	public void readFromCompound(TAG_Compound compound) {
		inhabitedTime = getLongValue(compound, inhabitedTimeTagName);
		lastUpdate = getLongValue(compound, lastUpdateTagName);
		lightPopulated = getByteValue(compound, lightPopulatedTagName);
		terrainPopulated = getByteValue(compound, terrainPopulatedTagName);
		xPos = getIntValue(compound, xPosTagName);
		zPos = getIntValue(compound, zPosTagName);
		biomes = getByteArrayValue(compound, biomesTagName);
		heightMap = getIntArrayValue(compound, heightMapTagName);
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_Long(inhabitedTimeTagName, inhabitedTime));
		compound.addTAG(new TAG_Long(lastUpdateTagName, lastUpdate));
		compound.addTAG(new TAG_Byte(lightPopulatedTagName, lightPopulated));
		compound.addTAG(new TAG_Byte(terrainPopulatedTagName, terrainPopulated));
		compound.addTAG(new TAG_Int(xPosTagName, xPos));
		compound.addTAG(new TAG_Int(zPosTagName, zPos));
		compound.addTAG(new TAG_Byte_Array(biomesTagName, biomes));
		compound.addTAG(new TAG_Int_Array(heightMapTagName, heightMap));
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
}
