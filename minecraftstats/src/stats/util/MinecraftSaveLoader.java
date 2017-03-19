package stats.util;

import java.io.File;
import java.io.IOException;

import stats.ui.model.MinecraftSaveData;

public final class MinecraftSaveLoader {

	public static MinecraftSaveData loadSave(File levelDat) throws IOException {
		MinecraftSaveData data = new MinecraftSaveData();
		
		data.setLevelDat(levelDat);
		
		return data;
	}
}
