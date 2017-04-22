package stats.nbt.model;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;

public class ItemModel {

	private byte count;
	private short damage;
	private String itemID;
	
	public void readFromCompound(TAG_Compound compound) {
		TAG_Byte tagCount = (TAG_Byte) compound.getTAG("Count");
		TAG_Short tagDamage = (TAG_Short) compound.getTAG("Damage");
		TAG_String tagID = (TAG_String) compound.getTAG("id");
		
		if (tagCount != null) {
			count = tagCount.getValue();
		}
		
		if (tagDamage != null) {
			damage = tagDamage.getValue();
		}
		
		if (tagID != null) {
			itemID = tagID.getValue();
		}
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_Byte("Count", count));
		compound.addTAG(new TAG_Short("Damage", damage));
		compound.addTAG(new TAG_String("id", itemID));
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
		return itemID;
	}
	
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
}
