package stats.analysis;

import stats.nbt.model.SubChunkSectionModel;

public class BlockCounter {

	int[] counts = new int[4096];
	
	public void count(SubChunkSectionModel subchunk) {
		byte[] blocks = subchunk.getBlocks();
		byte[] add = subchunk.getAdd();
		
		for (int i = 0; i < blocks.length; i++) {
			int blockID = blocks[i];
			if (add != null) {
				blockID |= i % 2 == 0 ? (add[i / 2] & 0x0F) << 8 : (add[i / 2] & 0xF0) << 4;
			}
			counts[blockID]++;
		}
	}
	
	public int getCount(int blockID) {
		return counts[blockID];
	}
}
