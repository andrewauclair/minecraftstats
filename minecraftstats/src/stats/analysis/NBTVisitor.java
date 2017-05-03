package stats.analysis;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;

public abstract class NBTVisitor {
	public abstract void accept(RegionModel region);
	public abstract void accept(ChunkModel chunk);
	public abstract void accept(SubChunkSectionModel subchunk);
}
