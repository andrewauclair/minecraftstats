package stats.nbt.utils;

import java.io.File;
import java.io.IOException;

import stats.nbt.model.NBTFile;
import stats.nbt.model.PlayerDataModel;

// loads a PlayerDataModel from player.dat file
public final class PlayerDataLoader {

	public static PlayerDataModel LoadPlayerData(String UUID) {
		
		PlayerDataModel data = null;
		
		try {
			NBTFile file = NBTFileHelper.readNBTFile(new File(UUID + ".dat"));
			
			if (file != null) {
				data = new PlayerDataModel(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
