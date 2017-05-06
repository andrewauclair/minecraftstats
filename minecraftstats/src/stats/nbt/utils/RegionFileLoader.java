package stats.nbt.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.tags.TAG_Compound;

public class RegionFileLoader {
	private static final int sectorSize = 4096;
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
			inStream.skip(sectorSize);
			sector++;
		}
	}
	
	private void readChunk() throws IOException, DataFormatException {
		int size = inStream.readInt();
		int compression = inStream.readByte();
		
		byte[] data = new byte[size - 1];
		inStream.read(data);
		
		int sectors = (size + 4) / sectorSize;
		
		if ((size + 4) % sectorSize != 0) {
			sectors++;
		}
		sector += sectors;
		
		int toSkip = ((size + 4 + sectorSize - 1) / sectorSize) * sectorSize;
		inStream.skip(toSkip - (size + 4));
		sector += (toSkip - (size + 4)) / sectorSize;
		
		if (compression == 2) {
			DataInputStream nbtIn = decompressBytes(data);
			
			TAG_Compound tag = (TAG_Compound)NBTFileHelper.readNextTag(nbtIn);
			
			ChunkModel chunk = new ChunkModel();
			chunk.readFromCompound(tag);
			
			region.addChunk(chunk);
		}
	}
	
	private DataInputStream decompressBytes(byte[] data) throws DataFormatException, IOException {
		byte[] temp = new byte[4096];
		
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
}
