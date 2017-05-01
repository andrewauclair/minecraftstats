package stats.spec.nbt.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;

public class RegionModelSpecification {
	RegionModel region;
	ChunkModel chunk;
	int x;
	int z;
	
	@Before
	public void setup() {
		region = new RegionModel();
		x = 3;
		z = 5;
		chunk = new ChunkModel();
		chunk.setxPos(x);
		chunk.setzPos(z);
	}
	
	@Test
	public void ShouldAddChunk() {
		region.addChunk(chunk);
		
		assertEquals(chunk, region.getChunk(x, z));
	}
	
	@Test
	public void ShouldRemoveChunk() {
		region.addChunk(chunk);
		
		assertEquals(chunk, region.getChunk(x, z));
		
		region.removeChunk(chunk);
		
		assertNull(region.getChunk(x, z));
	}
	
	@Test
	public void ShouldReturnChunkCount() {
		ChunkModel chunk2 = new ChunkModel();
		chunk2.setxPos(5);
		chunk2.setzPos(10);
		
		region.addChunk(chunk);
		region.addChunk(chunk2);
		
		assertEquals(2, region.getChunkCount());
	}
}
