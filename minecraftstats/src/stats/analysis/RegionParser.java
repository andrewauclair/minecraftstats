package stats.analysis;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;

public class RegionParser {
	private RegionModel region;
	public RegionParser(RegionModel region) {
		this.region = region;
	}
	
	public void parse(NBTVisitor visitor) {
		visitor.accept(region);
		for (ChunkModel chunk : region.getChunks()) {
			visitor.accept(chunk);
			for (SubChunkSectionModel subchunk : chunk.getSubchunks()) {
				visitor.accept(subchunk);
			}
		}
	}
}
