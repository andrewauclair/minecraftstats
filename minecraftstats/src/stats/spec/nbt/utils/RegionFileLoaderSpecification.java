package stats.spec.nbt.utils;

import static org.junit.Assert.*;
import static stats.nbt.utils.RegionFileLoader.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.utils.RegionFileLoader;

public class RegionFileLoaderSpecification {
	private RegionFileLoader loader;
	private int x;
	private int z;
	private byte[] chunkData = new byte[100];
	TAG_Compound payload;
	private int payloadSize;
	
	// TODO Expand this test to cover 2 loaded chunks
	
	@Before
	public void setup() throws IOException {
		loader = new RegionFileLoader();
		x = 16;
		z = 8;
		payload = new TAG_Compound("Root");
		TAG_Compound level = new TAG_Compound(ChunkModel.levelTagName);
		level.addTAG(new TAG_Int(ChunkModel.xPosTagName, x));
		level.addTAG(new TAG_Int(ChunkModel.zPosTagName, z));
		payload.addTAG(level);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		output.writeByte(TAG_Type.TAG_Compound.getValue());
		payload.writeToStream(output, true);
		
		Deflater compress = new Deflater();
		compress.setInput(byteStream.toByteArray());
		compress.finish();
		payloadSize = compress.deflate(chunkData);
	}
	
	@Test
	public void SholdSetConstants() {
		assertEquals(4096, locationsByteSize);
		assertEquals(4096, timestampsByteSize);
	}
	
	@Test
	public void ShouldReadFromStream() throws IOException, DataFormatException {
		int[] locations = new int[locationsByteSize / 4];
		locations[272] = 0x2345;

		int[] timestamps = new int[timestampsByteSize / 4];
		timestamps[272] = 0x5678;
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(locationsByteSize + timestampsByteSize + 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(locations);
        intBuffer.put(timestamps);
        intBuffer.put(payloadSize + 1);
        
        byte[] array = byteBuffer.array();
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(array.length + payloadSize + 1);
        byteBuffer2.put(array);
        byteBuffer2.put((byte)2);
        byteBuffer2.put(chunkData, 0, payloadSize);
        
        DataInputStream input = new DataInputStream(new ByteArrayInputStream(byteBuffer2.array()));
        
        RegionModel region = loader.createRegionFromStream(input);
        
        assertEquals(0x2345, loader.getLocation(16, 8));
        assertEquals(0x5678, loader.getTimestamp(16, 8));
        assertNotNull(region.getChunk(x, z));
	}
}
