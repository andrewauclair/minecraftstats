package stats.nbt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	private ArrayList<Integer> chunkOffsets = new ArrayList<>();
	
	private RegionModel region = new RegionModel();
	private DataInputStream inStream = null;
	private int sector = 0;
	
	private RegionFileLoader() {
		
	}
	
	public static RegionModel createRegionFromStream(DataInputStream input) throws IOException, DataFormatException {
		RegionFileLoader loader = new RegionFileLoader();
		
		loader.inStream = input;
		
		loader.readLocations();
		loader.readTimestamps();
		loader.readChunks();
		
		return loader.region;
	}
	
	private void readLocations() throws IOException {
		for (int i = 0; i < locations.length; i++) {
			locations[i] = inStream.readInt();
		}
		sector++;
	}
	
	private void readTimestamps() throws IOException {
		for (int i = 0; i < timestamps.length; i++) {
			timestamps[i] = inStream.readInt();
		}
		sector++;
	}
	
	private void buildLocationsList() {
		chunkOffsets.clear();
		for (int location : locations) {
			int offset = location >> 8;
			if (offset != 0) {
				chunkOffsets.add(offset);
			}
		}
		Collections.sort(chunkOffsets);
	}
	
	private void readChunks() throws IOException, DataFormatException {
		buildLocationsList();
		
		for (int offset : chunkOffsets) {
			skipSectors(offset - sector);
			readChunk();
		}
	}
	
	private void skipSectors(int toSkip) throws IOException {
		for (int i = 0; i < toSkip; i++) {
			byte[] data = new byte[4096];
			inStream.read(data);
			sector++;
		}
	}
	
	private void readChunk() throws IOException, DataFormatException {
		int size = inStream.readInt();
		int compression = inStream.readByte();
		
		byte[] data = new byte[size - 1];
		inStream.read(data);
		
		int sectors = data.length / 4096;
		
		if (data.length % 4096 != 0) {
			sectors++;
		}
		sector += sectors;
		
		int toSkip = ((size + 4 + 4096 - 1) / 4096) * 4096;
		byte[] junk = new byte[toSkip - (size + 4)];
		inStream.read(junk);
		
		if (compression == 2) {
			DataInputStream nbtIn = decompressBytes(data);
			
			TAG_Compound tag = (TAG_Compound)NBTFileHelper.readNextTag(nbtIn);
			
			ChunkModel chunk = new ChunkModel();
			chunk.readFromCompound(tag);
			
			region.addChunk(chunk);
		}
	}
	
	private DataInputStream decompressBytes(byte[] data) throws DataFormatException, IOException {
		byte[] temp = new byte[1024];
		
		Inflater decompress = new Inflater();
		decompress.setInput(data);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(byteStream);
		
		while (!decompress.finished()) {
			int count = decompress.inflate(temp);
			output.write(temp, 0, count);
		}
		
		return new DataInputStream(new ByteArrayInputStream(byteStream.toByteArray()));
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
		
		System.out.println("Loaded region with " + region.getChunkCount() + " chunks.");
	}
}
