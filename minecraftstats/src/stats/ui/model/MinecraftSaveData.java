package stats.ui.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import stats.nbt.model.NBTFile;
import stats.nbt.model.PlayerDataModel;
import stats.nbt.utils.NBTFileHelper;
import stats.nbt.utils.PlayerDataLoader;
import stats.util.MojangAPI;

public class MinecraftSaveData {

	private File m_levelDat;
	
	private NBTFile m_levelDatNBT;
	
	private ArrayList<PlayerDataModel> m_players = new ArrayList<>();
	
	public File getStatsDir() {
		return new File(m_levelDat.getParent() + "/stats");
	}
	
	public File getPlayerDir() {
		return new File(m_levelDat.getParent() + "/playerdata");
	}
	
	public NBTFile getLevelDatNBT() {
		return m_levelDatNBT;
	}
	
	public ArrayList<PlayerDataModel> getPlayers() {
		return m_players;
	}
	
	public void setLevelDat(File levelDatfile) throws IOException {
		m_levelDat = levelDatfile;
		m_levelDatNBT = NBTFileHelper.readNBTFile(levelDatfile);
		
		m_players.clear();
		
		File[] playerFiles = getPlayerDir().listFiles();
		
		for (File file : playerFiles) {
			if (file.isFile()) {
				m_players.add(PlayerDataLoader.LoadPlayerData(file));
			}
		}
	}
}
