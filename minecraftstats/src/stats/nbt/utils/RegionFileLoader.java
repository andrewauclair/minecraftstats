package stats.nbt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.tags.TAG_Compound;

public class RegionFileLoader {
	public static final int locationsByteSize = 4096;
	public static final int timestampsByteSize = 4096;
	
	private int[] locations = new int[locationsByteSize / 4];
	private int[] timestamps = new int[timestampsByteSize / 4];
	
	public RegionModel createRegionFromStream(DataInputStream input) throws IOException, DataFormatException {
		byte[] locs = new byte[locationsByteSize];
		input.read(locs);
		
		IntBuffer intBuffer = ByteBuffer.wrap(locs).asIntBuffer();
		intBuffer.get(locations);
		
		byte[] times = new byte[timestampsByteSize];
		input.read(times);
		
		intBuffer = ByteBuffer.wrap(times).asIntBuffer();
		intBuffer.get(timestamps);
		
		// loop through the locations to find all the chunks and load them
		RegionModel region = new RegionModel();
		
		// TODO can we just read the data and not care about the location or timestamp?
		while (input.available() > 0) {
			// read int for chunk size
			int size = input.readInt();
			
			// read byte for compression
			int compression = input.readByte();
			
			// read chunk data
			byte[] chunkData = new byte[size - 1];
			input.read(chunkData);
			
			int available = input.available();
			
			// default the table and read the NBT
			Inflater decompress = new Inflater();
			decompress.setInput(chunkData);
			
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(byteStream);
			
			byte[] bytes = new byte[1024];
			
			while (!decompress.finished()) {
				int count = decompress.inflate(bytes);
				output.write(bytes, 0, count);
			}
			
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(byteStream.toByteArray()));
			
			TAG_Compound tag = (TAG_Compound)NBTFileHelper.readNextTag(inputStream);
			
			ChunkModel chunk = new ChunkModel();
			chunk.readFromCompound(tag);
			
			region.addChunk(chunk);
		}
		
		return region;
	}
	
	public int getLocation(int x, int z) {
		return locations[getIndex(x, z)];
	}
	
	public int getTimestamp(int x, int z) {
		return timestamps[getIndex(x, z)];
	}
	
	private int getIndex(int x, int z) {
		return (x & 31) + (z & 31) * 32;
	}
}
