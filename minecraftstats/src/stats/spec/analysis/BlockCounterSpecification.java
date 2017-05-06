package stats.spec.analysis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stats.analysis.BlockCounter;
import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;

public class BlockCounterSpecification {
	BlockCounter counter;
	SubChunkSectionModel subchunk;
	
	@Before
	public void setup() {
		counter = new BlockCounter();
		subchunk = new SubChunkSectionModel();
	}
	
	@Test
	public void ShouldCountBlocksInSubChunk() {
		subchunk.setAdd(new byte[0]);
		
		byte[] blocks = new byte[5];
		blocks[0] = 1;
		blocks[1] = 1;
		blocks[2] = 2;
		blocks[3] = (byte)130;
		subchunk.setBlocks(blocks);
		
		counter.accept(subchunk);
		
		assertCounts(2, 1);
		assertCounts(1, 2);
		assertCounts(1, 130);
		assertCounts(1, 0);
	}
	
	@Test
	public void ShouldCountBlocksUsingAddDataField() {
		byte[] add = new byte[2];
		byte[] blocks = new byte[4];
		
		add[0] = (1 << 4) | 2;
		add[1] = 2;
		
		subchunk.setAdd(add);
		subchunk.setBlocks(blocks);
		
		counter.accept(subchunk);
		
		assertCounts(1, 256);
		assertCounts(2, 512);
		assertCounts(1, 0);
	}
	
	private void assertCounts(int expected, int blockID) {
		assertEquals(expected, counter.getChunkCount(blockID));
		assertEquals(expected, counter.getRegionCount(blockID));
	}
	
	@Test
	public void ShouldResetRegionCounts() {
		countBlocks();
		counter.accept(new RegionModel());
		assertEquals(0, counter.getRegionCount(1));
	}
	
	@Test
	public void ShouldResetChunkCounts() {
		countBlocks();
		counter.accept(new ChunkModel());
		assertEquals(0, counter.getChunkCount(1));
	}
	
	private void countBlocks() {
		byte[] blocks = new byte[] { 1, 1 };
		subchunk.setBlocks(blocks);
		subchunk.setAdd(new byte[0]);
		
		counter.accept(subchunk);
	}
}
