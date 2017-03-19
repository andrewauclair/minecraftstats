package stats.nbt.utils;

import java.io.File;
import java.io.IOException;

import stats.nbt.model.NBTFile;
import stats.nbt.model.PlayerDataModel;

// loads a PlayerDataModel from player.dat file
public final class PlayerDataLoader {

	public static PlayerDataModel LoadPlayerData(File file) {
		
		PlayerDataModel data = null;
		
		try {
			NBTFile nbtFile = NBTFileHelper.readNBTFile(file);
			
			if (nbtFile != null) {
				data = new PlayerDataModel(nbtFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
