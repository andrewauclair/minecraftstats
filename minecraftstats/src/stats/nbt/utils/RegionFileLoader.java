package stats.nbt.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class RegionFileLoader {
	public static final int locationsByteSize = 4096;
	public static final int timestampsByteSize = 4096;
	
	private int[] locations = new int[locationsByteSize / 4];
	private int[] timestamps = new int[timestampsByteSize / 4];
	
	public void createRegionFromStream(DataInputStream input) throws IOException {
		byte[] locs = new byte[locationsByteSize];
		input.read(locs);
		
		IntBuffer intBuffer = ByteBuffer.wrap(locs).asIntBuffer();
		intBuffer.get(locations);
		
		byte[] times = new byte[timestampsByteSize];
		input.read(times);
		
		intBuffer = ByteBuffer.wrap(times).asIntBuffer();
		intBuffer.get(timestamps);
	}
	
	public int getLocation(int x, int z) {
		return locations[((x & 31) + (z & 31) * 32)];
	}
	
	public int getTimestamp(int x, int z) {
		return timestamps[((x & 31) + (z & 31) * 32)];
	}
}
