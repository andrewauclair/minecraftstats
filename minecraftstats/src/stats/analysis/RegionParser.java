package stats.analysis;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

import stats.nbt.model.ChunkModel;
import stats.nbt.model.RegionModel;
import stats.nbt.model.SubChunkSectionModel;
import stats.nbt.utils.RegionFileLoader;

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
	
	public static void main(String[] args) throws FileNotFoundException, IOException, DataFormatException {
		BlockCounter count = new BlockCounter();
		
		File dir = new File(args[0]);
		File[] regionFiles = dir.listFiles();
		for (File file : regionFiles) {
			RegionModel region = RegionFileLoader.createRegionFromStream(new DataInputStream(new FileInputStream(file)));
			RegionParser parser = new RegionParser(region);
			parser.parse(count);
		}

//		System.out.println("Air blocks: " + count.getCount(0));
		System.out.println("Diamond Ore: " + count.getCount(56));
		System.out.println("Enchanting Table: " + count.getCount(116));
		System.out.println("Ender Chest: " + count.getCount(130));
//		System.out.println("Trapped Chest: " + count.getCount(146));
//		System.out.println("Stone: " + count.getCount(1));
//		System.out.println("Grass: " + count.getCount(2));
//		System.out.println("Dirt: " + count.getCount(3));
		System.out.println("Monster Spawner: " + count.getCount(52));
	}
}
