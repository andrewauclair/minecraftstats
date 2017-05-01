package stats.spec.analysis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stats.analysis.BlockCounter;
import stats.nbt.model.SubChunkSectionModel;

public class BlockCounterSpecification {
	BlockCounter counter;
	
	@Before
	public void setup() {
		counter = new BlockCounter();
	}
	
	@Test
	public void ShouldCountBlocksInSubChunk() {
		SubChunkSectionModel subchunk = new SubChunkSectionModel();
		subchunk.setAdd(null);
		
		byte[] blocks = new byte[4096];
		blocks[0] = 1;
		blocks[1] = 1;
		blocks[2] = 2;
		subchunk.setBlocks(blocks);
		
		counter.count(subchunk);
		
		assertEquals(2, counter.getCount(1));
		assertEquals(1, counter.getCount(2));
		assertEquals(4093, counter.getCount(0));
	}
	
	@Test
	public void ShouldCountBlocksUsingAddDataField() {
		SubChunkSectionModel subchunk = new SubChunkSectionModel();
		byte[] add = new byte[2048];
		byte[] blocks = new byte[4096];
		
		add[0] = (1 << 4) | 2;
		add[1] = 2;
		
		subchunk.setAdd(add);
		subchunk.setBlocks(blocks);
		
		counter.count(subchunk);
		
		assertEquals(1, counter.getCount(256));
		assertEquals(2, counter.getCount(512));
		assertEquals(4093, counter.getCount(0));
	}
}
