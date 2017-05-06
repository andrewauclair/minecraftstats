package stats.analysis;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;

public class BlockCounter extends NBTVisitor {
	private int[] counts;
	private int[] regionCounts;
	
	public BlockCounter() {
		resetRegionCount();
		resetChunkCounts();
	}
	
	public int getChunkCount(int blockID) {
		return counts[blockID];
	}

	public int getRegionCount(int blockID) {
		return regionCounts[blockID];
	}
	
	@Override
	public void accept(RegionModel region) {
		resetRegionCount();
	}

	private void resetRegionCount() {
		regionCounts = new int[4096];
	}
	
	@Override
	public void accept(ChunkModel chunk) {
		resetChunkCounts();
	}

	private void resetChunkCounts() {
		counts = new int[4096];
	}
	
	@Override
	public void accept(SubChunkSectionModel subchunk) {
		byte[] blocks = subchunk.getBlocks();
		byte[] add = subchunk.getAdd();
		
		for (int i = 0; i < blocks.length; i++) {
			int blockID = blocks[i] & 0xFF;
			if (add.length >= blocks.length / 2) {
				blockID |= i % 2 == 0 ? (add[i / 2] & 0x0F) << 8 : (add[i / 2] & 0xF0) << 4;
			}
			increment(blockID & 0xFFF);
		}
	}
	
	private void increment(int blockID) {
		counts[blockID]++;
		regionCounts[blockID]++;
	}
}
