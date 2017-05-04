package stats.analysis;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;

public class BlockCounter extends NBTVisitor {

	private int[] counts = new int[4096];
	
	public int getCount(int blockID) {
		return counts[blockID];
	}

	@Override
	public void accept(RegionModel region) {
		
	}

	@Override
	public void accept(ChunkModel chunk) {
		
	}

	@Override
	public void accept(SubChunkSectionModel subchunk) {
		byte[] blocks = subchunk.getBlocks();
		byte[] add = subchunk.getAdd();
		
		for (int i = 0; i < blocks.length; i++) {
			int blockID = blocks[i];
			if (add != null && add.length >= blocks.length / 2) {
				blockID |= i % 2 == 0 ? (add[i / 2] & 0x0F) << 8 : (add[i / 2] & 0xF0) << 4;
			}
			counts[blockID & 0xFF]++;
		}
	}
}
