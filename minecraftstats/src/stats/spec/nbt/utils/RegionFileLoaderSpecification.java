package stats.spec.nbt.utils;

import static org.junit.Assert.*;
import static stats.nbt.utils.RegionFileLoader.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.utils.RegionFileLoader;

// The regionFileLoader class will parse through a region file and create a 
// RegionModel out of all the chunks found in it

// load 3 chunks and assert that they were read with the correct data
public class RegionFileLoaderSpecification {
	private RegionFileLoader loader;
	private int x;
	private int z;
	
	// chunk: (16, 8)
	// location and timestamp: 272
	// chunk (16, 8) at: 8192
	
	@Before
	public void setup() {
		loader = new RegionFileLoader();
		x = 16;
		z = 8;
	}
	
	@Test
	public void SholdSetConstants() {
		assertEquals(4096, locationsByteSize);
		assertEquals(4096, timestampsByteSize);
	}
	
	@Test
	public void ShouldReadFromStream() throws IOException {
		int[] locations = new int[locationsByteSize / 4];
		locations[272] = 0x2345;

		int[] timestamps = new int[timestampsByteSize / 4];
		timestamps[272] = 0x5678;
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(locationsByteSize + timestampsByteSize);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(locations);
        intBuffer.put(timestamps);
        
        byte[] array = byteBuffer.array();
        
        DataInputStream input = new DataInputStream(new ByteArrayInputStream(array));
        
        // TODO This needs to return a RegionModel and we need to assert that it has 1 chunk
        loader.createRegionFromStream(input);
        
        assertEquals(0x2345, loader.getLocation(16, 8));
        assertEquals(0x5678, loader.getTimestamp(16, 8));
	}
}
