package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int;

public class PlayerDataModel {
	public static final String dimensionTagName = "Dimension";
	public static final String healthTagName = "Health";
	public static final String playerGameTypeTagName = "playerGameType";
	public static final String xpLevelTagName = "XpLevel";
	
	private String m_UUID = "";
	private int dimension;
	private float health;
	private int playerGameType;
	private int xpLevel;
	
	public PlayerDataModel() {
		
	}
	
	public PlayerDataModel(String UUID) {
		m_UUID = UUID;
	}
	
//	public PlayerDataModel(NBTFile file) {
//		m_UUID = file.getFile().getName().substring(0, file.getFile().getName().indexOf('.'));
//		
//		// TODO Move this to a utility class
//		try {
//			m_userName = MojangAPI.getUserName(m_UUID.replace("-", ""));
//		}
//		catch (Exception e) {
//			System.out.println("Error getting username");
//			e.printStackTrace();
//		}
//	}
	
	public void readFromCompound(TAG_Compound compound) {
		dimension = getIntValue(compound, dimensionTagName);
		health = getFloatValue(compound, healthTagName);
		playerGameType = getIntValue(compound, playerGameTypeTagName);
		xpLevel = getIntValue(compound, xpLevelTagName);
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_Int(dimensionTagName, dimension));
		compound.addTAG(new TAG_Float(healthTagName, health));
		compound.addTAG(new TAG_Int(playerGameTypeTagName, playerGameType));
		compound.addTAG(new TAG_Int(xpLevelTagName, xpLevel));
	}
	
	public String getUUID() {
		return m_UUID;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getPlayerGameType() {
		return playerGameType;
	}

	public void setPlayerGameType(int playerGameType) {
		this.playerGameType = playerGameType;
	}

	public int getXpLevel() {
		return xpLevel;
	}

	public void setXpLevel(int xpLevel) {
		this.xpLevel = xpLevel;
	}
}
