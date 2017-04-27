package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.SubChunkSectionModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.SubChunkSectionModel;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;

public class SubChunkSectionSpecification {
	SubChunkSectionModel chunkSection;
	private byte y;
	TAG_Byte_Array blockLight;
	TAG_Byte_Array blocks;
	TAG_Byte_Array data;
	TAG_Byte_Array skyLight;
	
	@Before
	public void setup() {
		chunkSection = new SubChunkSectionModel();
		y = 5;
		blockLight = new TAG_Byte_Array(blockLightTagName, new byte[] { 1, 2, 3});
		blocks = new TAG_Byte_Array(blocksTagName, new byte[] { 5, 6, 7});
		data = new TAG_Byte_Array(dataTagName, new byte[] { 8, 9, 10});
		skyLight = new TAG_Byte_Array(skyLightTagName, new byte[] { 11, 12, 13});
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("Y", yTagName);
		assertEquals("BlockLight", blockLightTagName);
		assertEquals("Blocks", blocksTagName);
		assertEquals("Data", dataTagName);
		assertEquals("SkyLight", skyLightTagName);
	}
	
	@Test
	public void ShouldReadFromCompound() {
		TAG_Compound compound = new TAG_Compound("");
		compound.addTAG(new TAG_Byte(yTagName, y));
		compound.addTAG(blockLight);
		compound.addTAG(blocks);
		compound.addTAG(data);
		compound.addTAG(skyLight);
		
		chunkSection.readFromCompound(compound);
		
		assertEquals(y, chunkSection.getY());
		assertEquals(blockLight.getValue(), chunkSection.getBlockLight());
		assertEquals(blocks.getValue(), chunkSection.getBlocks());
		assertEquals(data.getValue(), chunkSection.getData());
		assertEquals(skyLight.getValue(), chunkSection.getSkyLight());
	}
	
	@Test
	public void ShouldWriteToCompound() {
		TAG_Compound compound = new TAG_Compound("");
		
		chunkSection.setY(y);
		chunkSection.setBlockLight(blockLight.getValue());
		chunkSection.setBlocks(blocks.getValue());
		chunkSection.setData(data.getValue());
		chunkSection.setSkyLight(skyLight.getValue());
		
		chunkSection.writeToStream(compound);
		
		assertTagByte(y, yTagName, compound);
		assertTagByteArray(blockLight.getValue(), blockLightTagName, compound);
		assertTagByteArray(blocks.getValue(), blocksTagName, compound);
		assertTagByteArray(data.getValue(), dataTagName, compound);
		assertTagByteArray(skyLight.getValue(), skyLightTagName, compound);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		TAG_Compound compound = new TAG_Compound("");
		
		chunkSection.readFromCompound(compound);
	}
}
