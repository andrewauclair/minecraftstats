package stats.spec.analysis;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import stats.analysis.NBTVisitor;
import stats.analysis.RegionParser;
import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;

public class RegionParserSpecification {
	@Test
	public void ShouldCallVisitorWithEachModel() {
		RegionModel region = new RegionModel();
		ChunkModel chunk1 = new ChunkModel();
		chunk1.setxPos(5);
		chunk1.setzPos(5);
		ChunkModel chunk2 = new ChunkModel();
		chunk2.setxPos(10);
		chunk2.setzPos(10);
		SubChunkSectionModel subchunk1 = new SubChunkSectionModel();
		SubChunkSectionModel subchunk2 = new SubChunkSectionModel();
		SubChunkSectionModel subchunk3 = new SubChunkSectionModel();
		SubChunkSectionModel subchunk4 = new SubChunkSectionModel();
		ArrayList<SubChunkSectionModel> subchunks1 = new ArrayList<>();
		subchunks1.add(subchunk1);
		subchunks1.add(subchunk2);
		ArrayList<SubChunkSectionModel> subchunks2 = new ArrayList<>();
		subchunks2.add(subchunk3);
		subchunks2.add(subchunk4);
		
		chunk1.setSubchunks(subchunks1);
		chunk2.setSubchunks(subchunks2);
		region.addChunk(chunk1);
		region.addChunk(chunk2);
		
		NBTVisitor visitor = Mockito.mock(NBTVisitor.class);
		
		RegionParser parser = new RegionParser(region);
		
		parser.parse(visitor);
		
		Mockito.verify(visitor).accept(region);
		Mockito.verify(visitor).accept(chunk1);
		Mockito.verify(visitor).accept(chunk2);
		Mockito.verify(visitor).accept(subchunk1);
		Mockito.verify(visitor).accept(subchunk2);
		Mockito.verify(visitor).accept(subchunk3);
		Mockito.verify(visitor).accept(subchunk4);
	}
}
