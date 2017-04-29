package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.ChunkSectionsModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ChunkSectionsModel;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_Long;

public class ChunkSectionsModelSpecification {
	private ChunkSectionsModel chunkSections;
	private TAG_Compound compound;
	private TAG_Compound level;
	
	private long inhabitedTime;
	private long lastUpdate;
	private byte lightPopulated;
	private byte terrainPopulated;
	private int xPos;
	private int zPos;
	private TAG_Byte_Array biomes;
	private TAG_Int_Array heightMap;
	private int dataVersion;
	
	@Before
	public void setup() {
		chunkSections = new ChunkSectionsModel();
		compound = new TAG_Compound("");
		level = new TAG_Compound(levelTagName);
		
		inhabitedTime = 100;
		lastUpdate = 44;
		lightPopulated = 1;
		terrainPopulated = 3;
		xPos = 222;
		zPos = 333;
		biomes = new TAG_Byte_Array(biomesTagName, new byte[] { 1, 2});
		heightMap = new TAG_Int_Array(heightMapTagName, new int[] { 3, 4 });
		dataVersion = 922;
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("Level", levelTagName);
		assertEquals("InhabitedTime", inhabitedTimeTagName);
		assertEquals("LastUpdate", lastUpdateTagName);
		assertEquals("LightPopulated", lightPopulatedTagName);
		assertEquals("TerrainPopulated", terrainPopulatedTagName);
		assertEquals("xPos", xPosTagName);
		assertEquals("zPos", zPosTagName);
		assertEquals("Biomes", biomesTagName);
		assertEquals("HeightMap", heightMapTagName);
		assertEquals("DataVersion", dataVersionTagName);
	}
	
	@Test
	public void ShouldReadFromCompound() {
		compound.addTAG(level);
		level.addTAG(new TAG_Long(inhabitedTimeTagName, inhabitedTime));
		level.addTAG(new TAG_Long(lastUpdateTagName, lastUpdate));
		level.addTAG(new TAG_Byte(lightPopulatedTagName, lightPopulated));
		level.addTAG(new TAG_Byte(terrainPopulatedTagName, terrainPopulated));
		level.addTAG(new TAG_Int(xPosTagName, xPos));
		level.addTAG(new TAG_Int(zPosTagName, zPos));
		level.addTAG(biomes);
		level.addTAG(heightMap);
		compound.addTAG(new TAG_Int(dataVersionTagName, dataVersion));
		
		chunkSections.readFromCompound(compound);
		
		assertEquals(inhabitedTime, chunkSections.getInhabitedTime());
		assertEquals(lastUpdate, chunkSections.getLastUpdate());
		assertEquals(lightPopulated, chunkSections.getLightPopulated());
		assertEquals(terrainPopulated, chunkSections.getTerrainPopulated());
		assertEquals(xPos, chunkSections.getxPos());
		assertEquals(zPos, chunkSections.getzPos());
		assertTrue(Arrays.equals(biomes.getValue(), chunkSections.getBiomes()));
		assertTrue(Arrays.equals(heightMap.getValue(), chunkSections.getHeightMap()));
		assertEquals(dataVersion, chunkSections.getDataVersion());
	}
	
	@Test
	public void ShouldWriteToCompound() {
		chunkSections.setInhabitedTime(inhabitedTime);
		chunkSections.setLastUpdate(lastUpdate);
		chunkSections.setLightPopulated(lightPopulated);
		chunkSections.setTerrainPopulated(terrainPopulated);
		chunkSections.setxPos(xPos);
		chunkSections.setzPos(zPos);
		chunkSections.setBiomes(biomes.getValue());
		chunkSections.setHeightMap(heightMap.getValue());
		chunkSections.setDataVersion(dataVersion);
		
		chunkSections.writeToCompound(compound);
		level = (TAG_Compound)compound.getTAG(levelTagName);
		
		assertTagLong(inhabitedTime, inhabitedTimeTagName, level);
		assertTagLong(lastUpdate, lastUpdateTagName, level);
		assertTagByte(lightPopulated, lightPopulatedTagName, level);
		assertTagByte(terrainPopulated, terrainPopulatedTagName, level);
		assertTagInt(xPos, xPosTagName, level);
		assertTagInt(zPos, zPosTagName, level);
		assertTagInt(dataVersion, dataVersionTagName, compound);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		chunkSections.readFromCompound(compound);
	}
}
