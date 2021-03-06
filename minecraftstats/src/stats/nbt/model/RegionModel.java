package stats.nbt.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegionModel {
	Map<Integer, ChunkModel> chunks = new HashMap<Integer, ChunkModel>();
	
	public void addChunk(ChunkModel chunk) {
		chunks.put(getIndex(chunk.getxPos(), chunk.getzPos()), chunk);
	}
	
	public void removeChunk(ChunkModel chunk) {
		chunks.remove(getIndex(chunk.getxPos(), chunk.getzPos()));
	}
	
	public ChunkModel getChunk(int x, int z) {
		return chunks.get(getIndex(x, z));
	}
	
	public int getChunkCount() {
		return chunks.size();
	}
	
	public ArrayList<ChunkModel> getChunks() {
		ArrayList<ChunkModel> chunks = new ArrayList<>();
		for (ChunkModel chunk : this.chunks.values()) {
			chunks.add(chunk);
		}
		return chunks;
	}
	
	private Integer getIndex(int x, int z) {
		return x + z * 32;
	}
}
