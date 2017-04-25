package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;

public class ItemModel {
	public static final String countTagName = "Count";
	public static final String damageTagName = "Damage";
	public static final String idTagName = "id";
	
	private byte count;
	private short damage;
	private String id;
	
	public void readFromCompound(TAG_Compound compound) {
		count = getByteValue(compound, countTagName);
		damage = getShortValue(compound, damageTagName);
		id = getStringValue(compound, idTagName);
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_Byte(countTagName, count));
		compound.addTAG(new TAG_Short(damageTagName, damage));
		compound.addTAG(new TAG_String(idTagName, id));
	}
	
	public byte getCount() {
		return count;
	}
	
	public void setCount(byte count) {
		this.count = count;
	}
	
	public short getDamage() {
		return damage;
	}
	
	public void setDamage(short damage) {
		this.damage = damage;
	}
	
	public String getItemID() {
		return id;
	}
	
	public void setItemID(String itemID) {
		this.id = itemID;
	}
}
