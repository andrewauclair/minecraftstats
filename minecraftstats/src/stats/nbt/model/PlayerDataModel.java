package stats.nbt.model;

import stats.util.MojangAPI;

public class PlayerDataModel {

	private String m_UUID = "";
	private String m_userName = "";
	private NBTFile m_file = null;
	
	public PlayerDataModel() {
		
	}
	
	public PlayerDataModel(String userName, String UUID) {
		m_userName = userName;
		m_UUID = UUID;
	}
	
	public PlayerDataModel(NBTFile file) {
		m_file = file;
		m_UUID = file.getFile().getName().substring(0, file.getFile().getName().indexOf('.'));
		
		// TODO Move this to a utility class
		try {
			m_userName = MojangAPI.getUserName(m_UUID.replace("-", ""));
		}
		catch (Exception e) {
			System.out.println("Error getting username");
			e.printStackTrace();
		}
	}
	
	public String getUUID() {
		return m_UUID;
	}
	
	public String getUserName() {
		return m_userName;
	}
}
