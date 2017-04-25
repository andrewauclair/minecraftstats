package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;

public class ChunkSectionModel {
	public static final String yTagName = "Y";
	public static final String blockLightTagName = "BlockLight";
	public static final String blocksTagName = "Blocks";
	public static final String dataTagName = "Data";
	public static final String skyLightTagName = "SkyLight";
	
	private byte y;

	public void readFromCompound(TAG_Compound compound) {
		y = getByteValue(compound, yTagName);
	}
	
	public void writeToStream(TAG_Compound compound) {
		compound.addTAG(new TAG_Byte(yTagName, y));
	}
	
	public byte getY() {
		return y;
	}

	public void setY(byte y) {
		this.y = y;
	}
}
