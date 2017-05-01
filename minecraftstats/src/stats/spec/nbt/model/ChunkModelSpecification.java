package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.ChunkModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.SubChunkSectionModel;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_Long;

public class ChunkModelSpecification {
	private ChunkModel chunkSections;
	private TAG_Compound compound;
	private TAG_Compound level;
	private SubChunkSectionModel subchunk1;
	private SubChunkSectionModel subchunk2;
	
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
		chunkSections = new ChunkModel();
		compound = new TAG_Compound("");
		level = new TAG_Compound(levelTagName);
		subchunk1 = new SubChunkSectionModel();
		subchunk2 = new SubChunkSectionModel();
		subchunk1.setY((byte)0);
		subchunk2.setY((byte)1);
		
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
		assertEquals("Entities", entitiesTagName);
		assertEquals("Sections", sectionsTagName);
		assertEquals("TileEntities", tileEntitiesTagName);
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
		
		TAG_Compound subchunk1_tag = new TAG_Compound("");
		TAG_Compound subchunk2_tag = new TAG_Compound("");
		subchunk1.writeToCompound(subchunk1_tag);
		subchunk2.writeToCompound(subchunk2_tag);
		TAG_List sections = new TAG_List(sectionsTagName);
		sections.addTAG(subchunk1_tag);
		sections.addTAG(subchunk2_tag);
		level.addTAG(sections);
		
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
		
		ArrayList<SubChunkSectionModel> subchunks = chunkSections.getSubchunks();
		
		assertEquals(2, subchunks.size());
		assertEquals(subchunk1.getY(), subchunks.get(0).getY());
		assertEquals(subchunk2.getY(), subchunks.get(1).getY());
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
		
		ArrayList<SubChunkSectionModel> subchunks = new ArrayList<>();
		subchunks.add(subchunk1);
		subchunks.add(subchunk2);
		chunkSections.setSubchunks(subchunks);
		
		chunkSections.writeToCompound(compound);
		
		level = (TAG_Compound)compound.getTAG(levelTagName);
		
		TAG_List sections = (TAG_List)level.getTAG(sectionsTagName);
		ArrayList<TAG> tags = sections.getValue();
		
		assertTagLong(inhabitedTime, inhabitedTimeTagName, level);
		assertTagLong(lastUpdate, lastUpdateTagName, level);
		assertTagByte(lightPopulated, lightPopulatedTagName, level);
		assertTagByte(terrainPopulated, terrainPopulatedTagName, level);
		assertTagInt(xPos, xPosTagName, level);
		assertTagInt(zPos, zPosTagName, level);
		assertTagInt(dataVersion, dataVersionTagName, compound);
		assertEquals(2, tags.size());
		assertSubchunkTags(subchunk1.getY(), (TAG_Compound)tags.get(0));
		assertSubchunkTags(subchunk2.getY(), (TAG_Compound)tags.get(1));
	}
	
	private void assertSubchunkTags(byte y, TAG_Compound compound) {
		assertTagByte(y, SubChunkSectionModel.yTagName, compound);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		chunkSections.readFromCompound(compound);
	}
}
