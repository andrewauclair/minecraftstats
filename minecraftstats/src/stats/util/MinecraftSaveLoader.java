package stats.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.utils.NBTFileHelper;
import stats.nbt.utils.PlayerDataLoader;
import stats.ui.model.MinecraftSaveData;

public final class MinecraftSaveLoader {

	public static MinecraftSaveData loadSave(File levelDat) throws IOException {
		MinecraftSaveData data = new MinecraftSaveData();
		
		TAG_Compound root = (TAG_Compound)NBTFileHelper.readNBTFile(levelDat).getRoot();
		data.setRoot(root);
		
		File[] playerFiles = new File(getPlayerDir(levelDat)).listFiles();
		
		for (File file : playerFiles) {
			if (file.isFile()) {
				data.addPlayer(PlayerDataLoader.LoadPlayerData(file));
			}
		}
		
		File[] statsFiles = new File(getStatsDir(levelDat)).listFiles();
		
		for (File stats : statsFiles) {
			if (stats.isFile()) {
				Scanner scan = new Scanner(stats);
				String contents = "";
				while (scan.hasNextLine()) {
					contents += scan.nextLine();
				}
				JsonObject json = new JsonParser().parse(contents).getAsJsonObject();
				
				long minutes = json.get("stat.playOneMinute").getAsLong();
				System.out.println("Minutes Played: " + minutes);
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
