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
	ChunkSectionsModel chunkSections;
	private TAG_Compound compound;
	
	private long inhabitedTime;
	private long lastUpdate;
	private byte lightPopulated;
	private byte terrainPopulated;
	private int xPos;
	private int zPos;
	private TAG_Byte_Array biomes;
	private TAG_Int_Array heightMap;
	
	@Before
	public void setup() {
		chunkSections = new ChunkSectionsModel();
		compound = new TAG_Compound("");
		
		inhabitedTime = 100;
		lastUpdate = 44;
		lightPopulated = 1;
		terrainPopulated = 3;
		xPos = 222;
		zPos = 333;
		biomes = new TAG_Byte_Array(biomesTagName, new byte[] { 1, 2});
		heightMap = new TAG_Int_Array(heightMapTagName, new int[] { 3, 4 });
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("InhabitedTime", inhabitedTimeTagName);
		assertEquals("LastUpdate", lastUpdateTagName);
		assertEquals("LightPopulated", lightPopulatedTagName);
		assertEquals("TerrainPopulated", terrainPopulatedTagName);
		assertEquals("xPos", xPosTagName);
		assertEquals("zPos", zPosTagName);
		assertEquals("Biomes", biomesTagName);
		assertEquals("HeightMap", heightMapTagName);
	}
	
	@Test
	public void ShouldReadFromCompound() {
		compound.addTAG(new TAG_Long(inhabitedTimeTagName, inhabitedTime));
		compound.addTAG(new TAG_Long(lastUpdateTagName, lastUpdate));
		compound.addTAG(new TAG_Byte(lightPopulatedTagName, lightPopulated));
		compound.addTAG(new TAG_Byte(terrainPopulatedTagName, terrainPopulated));
		compound.addTAG(new TAG_Int(xPosTagName, xPos));
		compound.addTAG(new TAG_Int(zPosTagName, zPos));
		compound.addTAG(biomes);
		compound.addTAG(heightMap);
		
		chunkSections.readFromCompound(compound);
		
		assertEquals(inhabitedTime, chunkSections.getInhabitedTime());
		assertEquals(lastUpdate, chunkSections.getLastUpdate());
		assertEquals(lightPopulated, chunkSections.getLightPopulated());
		assertEquals(terrainPopulated, chunkSections.getTerrainPopulated());
		assertEquals(xPos, chunkSections.getxPos());
		assertEquals(zPos, chunkSections.getzPos());
		assertTrue(Arrays.equals(biomes.getValue(), chunkSections.getBiomes()));
		assertTrue(Arrays.equals(heightMap.getValue(), chunkSections.getHeightMap()));
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
		
		chunkSections.writeToCompound(compound);
		
		assertTagLong(inhabitedTime, inhabitedTimeTagName, compound);
		assertTagLong(lastUpdate, lastUpdateTagName, compound);
		assertTagByte(lightPopulated, lightPopulatedTagName, compound);
		assertTagByte(terrainPopulated, terrainPopulatedTagName, compound);
		assertTagInt(xPos, xPosTagName, compound);
		assertTagInt(zPos, zPosTagName, compound);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		chunkSections.readFromCompound(compound);
	}
}
