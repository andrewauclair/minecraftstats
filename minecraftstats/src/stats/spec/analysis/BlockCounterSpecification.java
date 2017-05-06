package stats.spec.analysis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stats.analysis.BlockCounter;
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
		
		byte[] blocks = new byte[4096];
		blocks[0] = 1;
		blocks[1] = 1;
		blocks[2] = 2;
		blocks[3] = (byte)130;
		subchunk.setBlocks(blocks);
		
		counter.accept(subchunk);
		
		assertEquals(2, counter.getCount(1));
		assertEquals(1, counter.getCount(2));
		assertEquals(1, counter.getCount(130));
		assertEquals(4092, counter.getCount(0));
	}
	
	@Test
	public void ShouldCountBlocksUsingAddDataField() {
		byte[] add = new byte[2048];
		byte[] blocks = new byte[4096];
		
		add[0] = (1 << 4) | 2;
		add[1] = 2;
		
		subchunk.setAdd(add);
		subchunk.setBlocks(blocks);
		
		counter.accept(subchunk);
		
		assertEquals(1, counter.getCount(256));
		assertEquals(2, counter.getCount(512));
		assertEquals(4093, counter.getCount(0));
	}
}
