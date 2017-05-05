package stats.spec.nbt.utils;

import static org.junit.Assert.*;
import static stats.nbt.utils.RegionFileLoader.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.utils.RegionFileLoader;
import stats.spec.nbt.model.SpecTagHelper;

public class RegionFileLoaderSpecification extends SpecTagHelper {
	private RegionFileLoader loader;
	int[] locations;
	int[] timestamps;
	ChunkModel[] chunks = new ChunkModel[2];
	
	@Before
	public void setup() throws IOException {
		super.setup();
		
		loader = new RegionFileLoader();
		
		locations = new int[locationsByteSize / 4];
		timestamps = new int[timestampsByteSize / 4];
		chunks[0] = createChunk(16, 8);
		chunks[1] = createChunk(18, 20);
	}
	
	private ChunkModel createChunk(int x, int z) {
		TAG_Compound compound = new TAG_Compound("Root");
		TAG_Compound level = new TAG_Compound(ChunkModel.levelTagName);
		level.addTAG(new TAG_Int(ChunkModel.xPosTagName, x));
		level.addTAG(new TAG_Int(ChunkModel.zPosTagName, z));
		compound.addTAG(level);
		ChunkModel chunk = new ChunkModel();
		chunk.readFromCompound(compound);
		return chunk;
	}
	
	@Test
	public void SholdSetConstants() {
		assertEquals(4096, locationsByteSize);
		assertEquals(4096, timestampsByteSize);
	}
	
	@Test
	public void ShouldReadFromStream() throws IOException, DataFormatException {
		setChunkLocation(chunks[0], 2);
		setChunkLocation(chunks[1], 4);
		
		int chunk1Timestamp = 0x1234;
		int chunk2Timestamp = 0x2345;
		
		setChunkTimestamp(chunks[0], chunk1Timestamp);
		setChunkTimestamp(chunks[1], chunk2Timestamp);
		
		writeLocationsToStream();
		writeTimestampsToStream();
		
		writeChunkToStream(chunks[0]);
		byte[] padding = new byte[4096];
		outStream.write(padding);
		writeChunkToStream(chunks[1]);
		
		createInputStreamFromOutputStream();
		
        RegionModel region = loader.createRegionFromStream(inStream);
        
        assertChunkCreation(region);
	}

	private void assertChunkCreation(RegionModel region) {
		ChunkModel[] chunksIn = new ChunkModel[2];
        chunksIn[0] = region.getChunk(chunks[0].getxPos(), chunks[0].getzPos());
        chunksIn[1] = region.getChunk(chunks[1].getxPos(), chunks[1].getzPos());
        assertNotNull(chunksIn[0]);
        assertNotNull(chunksIn[1]);
        assertEquals(chunksIn[0].getxPos(), chunksIn[0].getxPos());
        assertEquals(chunksIn[0].getzPos(), chunksIn[0].getzPos());
        assertEquals(chunksIn[1].getxPos(), chunksIn[1].getxPos());
        assertEquals(chunksIn[1].getzPos(), chunksIn[1].getzPos());
	}
	
	@Test
	public void ShouldReadChunkWithMultipleSectors() throws IOException, DataFormatException {
		setChunkLocation(chunks[0], 2);
		setChunkLocation(chunks[1], 4);
		
		TAG_Compound root = new TAG_Compound("Root");
		chunks[0].writeToCompound(root);
		
		for (int i = 0; i < 1300; i++) {
			root.addTAG(new TAG_Byte("Byte" + i, (byte)i));
		}
		
		writeLocationsToStream();
		writeTimestampsToStream();
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		output.writeByte(TAG_Type.TAG_Compound.getValue());
		root.writeToStream(output, true);
		
		writeByteStreamData(byteStream);
		writeChunkToStream(chunks[1]);
		
		createInputStreamFromOutputStream();
		
		RegionModel region = loader.createRegionFromStream(inStream);

        assertChunkCreation(region);
	}
	
	private void setChunkLocation(ChunkModel chunk, int offset) {
		int x = chunk.getxPos();
		int z = chunk.getzPos();
		locations[(x & 31) + (z & 31) * 32] = (offset << 8) | 1;
	}
	
	private void setChunkTimestamp(ChunkModel chunk, int timestamp) {
		int x = chunk.getxPos();
		int z = chunk.getzPos();
		timestamps[(x & 31) + (z & 31) * 32] = timestamp;
	}
	
	private void writeLocationsToStream() throws IOException {
		for (int loc : locations) {
			outStream.writeInt(loc);
		}
	}
	
	private void writeTimestampsToStream() throws IOException {
		for (int time : timestamps) {
			outStream.writeInt(time);
		}
	}
	
	private void writeChunkToStream(ChunkModel chunk) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		TAG_Compound root = new TAG_Compound("Root");
		chunk.writeToCompound(root);
		output.writeByte(TAG_Type.TAG_Compound.getValue());
		root.writeToStream(output, true);
		
		writeByteStreamData(byteStream);
	}

	private void writeByteStreamData(ByteArrayOutputStream byteStream) throws IOException {
		ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();
		
		Deflater compress = new Deflater();
		compress.setInput(byteStream.toByteArray());
		compress.finish();
		byte[] data = new byte[1024];
		while (!compress.finished()) {
			int count = compress.deflate(data);
			compressedStream.write(data, 0, count);
		}
		byte[] out = compressedStream.toByteArray();
		outStream.writeInt(out.length + 1);
		outStream.writeByte((byte)2);
		outStream.write(out);
		
		int sectors = ((out.length + 5 + 4096 - 1) / 4096) * 4096;
		byte[] padding = new byte[sectors - (out.length + 5)];
		outStream.write(padding);
	}
}
