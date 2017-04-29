package stats.ui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import stats.nbt.model.PlayerDataModel;
import stats.nbt.model.tags.TAG_Compound;

public class MinecraftSaveData {
	private TAG_Compound root;
	private Map<String, PlayerDataModel> players = new HashMap<>();
	
	public ArrayList<PlayerDataModel> getPlayers() {
		ArrayList<PlayerDataModel> players = new ArrayList<>();
		for (PlayerDataModel player : this.players.values()) {
			players.add(player);
		}
		return players;
	}
	
	public void setRoot(TAG_Compound root) {
		this.root = root;
	}
	
	public TAG_Compound getRoot() {
		return root;
	}
	
	public void addPlayer(PlayerDataModel player) {
		players.put(player.getUUID(), player);
	}
	
	public void removePlayer(PlayerDataModel player) {
		players.remove(player.getUUID());
	}
	
	public PlayerDataModel getPlayer(String UUID) {
		return players.get(UUID);
	}
}
