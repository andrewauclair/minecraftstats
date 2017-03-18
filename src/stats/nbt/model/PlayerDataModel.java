package stats.nbt.model;

import stats.util.MojangAPI;

public class PlayerDataModel {

	private String m_UUID = "";
	private String m_userName = "";
	private NBTFile m_file = null;
	
	public PlayerDataModel() {
		
	}
	
	public PlayerDataModel(NBTFile file) {
		m_file = file;
		m_UUID = file.getFile().getName().substring(0, file.getFile().getName().indexOf('.'));
		
		try {
			m_userName = MojangAPI.getUserName(m_UUID);
		}
		catch (Exception e) {
			System.out.println("Error getting username");
			e.printStackTrace();
		}
	}
}
