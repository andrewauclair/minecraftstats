package stats.util;

import java.io.File;
import java.io.IOException;

import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.utils.NBTFileHelper;
import stats.nbt.utils.PlayerDataLoader;
import stats.ui.model.MinecraftSaveData;

public final class MinecraftSaveLoader {

	public static MinecraftSaveData loadSave(File levelDat) throws IOException {
		MinecraftSaveData data = new MinecraftSaveData();
		
		//data.setLevelDat(levelDat);
		TAG_Compound root = (TAG_Compound)NBTFileHelper.readNBTFile(levelDat).getRoot();
		data.setRoot(root);
		
		File[] playerFiles = new File(getPlayerDir(levelDat)).listFiles();
		
		for (File file : playerFiles) {
			if (file.isFile()) {
				data.addPlayer(PlayerDataLoader.LoadPlayerData(file));
			}
		}
		return data;
	}
	
	private static String getStatsDir(File levelDat) {
		return levelDat.getParent() + "/stats";
	}
	
	private static String getPlayerDir(File levelDat) {
		return levelDat.getParent() + "/playerdata";
	}
}
