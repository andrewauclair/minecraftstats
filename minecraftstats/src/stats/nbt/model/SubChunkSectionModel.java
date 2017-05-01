package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;

public class SubChunkSectionModel {
	public static final String yTagName = "Y";
	public static final String blockLightTagName = "BlockLight";
	public static final String blocksTagName = "Blocks";
	public static final String dataTagName = "Data";
	public static final String skyLightTagName = "SkyLight";
	
	private byte y;
	private byte[] blockLight;
	private byte[] blocks;
	private byte[] data;
	private byte[] skyLight;
	
	public void readFromCompound(TAG_Compound compound) {
		y = getByteValue(compound, yTagName);
		blockLight = getByteArrayValue(compound, blockLightTagName);
		blocks = getByteArrayValue(compound, blocksTagName);
		data = getByteArrayValue(compound, dataTagName);
		skyLight = getByteArrayValue(compound, skyLightTagName);
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_Byte(yTagName, y));
		compound.addTAG(new TAG_Byte_Array(blockLightTagName, blockLight));
		compound.addTAG(new TAG_Byte_Array(blocksTagName, blocks));
		compound.addTAG(new TAG_Byte_Array(dataTagName, data));
		compound.addTAG(new TAG_Byte_Array(skyLightTagName, skyLight));
	}
	
	public byte getY() {
		return y;
	}

	public void setY(byte y) {
		this.y = y;
	}

	public byte[] getBlockLight() {
		return blockLight;
	}

	public void setBlockLight(byte[] blockLight) {
		this.blockLight = blockLight;
	}

	public byte[] getBlocks() {
		return blocks;
	}

	public void setBlocks(byte[] blocks) {
		this.blocks = blocks;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getSkyLight() {
		return skyLight;
	}

	public void setSkyLight(byte[] skyLight) {
		this.skyLight = skyLight;
	}
}
