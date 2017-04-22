package stats.nbt.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ItemModel {

	private byte count;
	private short damage;
	private String itemID;
	
	public void readFromStream(DataInputStream input) throws IOException {
		readString(input);
		count = input.readByte();
		readString(input);
		damage = input.readShort();
		readString(input);
		itemID = readString(input);
	}
	
	public void writeToStream(DataOutputStream output) throws IOException {
		writeString(output, "Count");
		output.writeByte(count);
		writeString(output, "Damage");
		output.writeShort(damage);
		writeString(output, "id");
		writeString(output, itemID);
	}
	
	private String readString(DataInputStream input) throws IOException {
		short len = input.readShort();
		byte[] bytes = new byte[len];
		input.read(bytes);
		return new String(bytes, "UTF-8");
	}
	
	private void writeString(DataOutputStream output, String value) throws IOException {
		output.writeShort(value.length());
		output.write(value.getBytes());
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
