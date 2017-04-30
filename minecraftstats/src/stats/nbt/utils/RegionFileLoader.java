package stats.nbt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
		
		int fileLoc = 4096 * 2;
		
		// TODO can we just read the data and not care about the location or timestamp?
		while (input.available() > 0) {
			System.out.println("fileLoc: " + fileLoc);
			// read int for chunk size
			int size = input.readInt();
			fileLoc += 4;
			fileLoc += size;
			
			// read byte for compression
			int compression = input.readByte();
			System.out.println("compression: " + compression);
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
			
			int n = ((size + 4 + 4096 - 1) / 4096) * 4096;
			byte[] extra = new byte[n - (size + 4)];
			fileLoc += n - (size + 4);
			System.out.println("size: " + size);
			System.out.println("n: " + n);
			input.read(extra);
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
	
	public static void main(String[] args) throws IOException, DataFormatException {
		loadRegion("C:\\Users\\might\\AppData\\Roaming\\.minecraft\\saves\\New World\\region\\r.0.0.mca");
		loadRegion("C:\\Users\\might\\AppData\\Roaming\\.minecraft\\saves\\New World\\region\\r.-1.0.mca");
		loadRegion("C:\\Users\\might\\AppData\\Roaming\\.minecraft\\saves\\New World\\region\\r.0.-1.mca");
		loadRegion("C:\\Users\\might\\AppData\\Roaming\\.minecraft\\saves\\New World\\region\\r.-1.-1.mca");
	}
	
	private static void loadRegion(String path) throws IOException, DataFormatException {
		FileInputStream input = new FileInputStream(new File(path));
		RegionModel region = new RegionFileLoader().createRegionFromStream(new DataInputStream(input));
	}
}
