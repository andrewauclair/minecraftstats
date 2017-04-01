package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import stats.nbt.utils.NBTInputStream;
import stats.nbt.utils.NBTOutputStream;

// base class for all tags
public abstract class TAG {

	public enum TAG_Type {
		TAG_End(0),
		TAG_Byte(1),
		TAG_Short(2),
		TAG_Int(3),
		TAG_Long(4),
		TAG_Float(5),
		TAG_Double(6),
		TAG_Byte_Array(7),
		TAG_String(8),
		TAG_List(9),
		TAG_Compound(10),
		TAG_Int_Array(11);
		
		private int m_value = 0;
		private TAG_Type(int value) {
			m_value = value;
		}
		
		public int getValue() {
			return m_value;
		}
		
		public static TAG_Type valueOf(int value) {
			for (TAG_Type val : values()) {
				if (val.m_value == value) {
					return val;
				}
			}
			return null;
		}
		
		public static TAG_Type fromTAG(TAG tag) {
			if (tag instanceof TAG_End) {
				return TAG_End;
			}
			else if (tag instanceof TAG_Byte) {
				return TAG_Byte;
			}
			else if (tag instanceof TAG_Short) {
				return TAG_Short;
			}
			else if (tag instanceof TAG_Int) {
				return TAG_Int;
			}
			else if (tag instanceof TAG_Long) {
				return TAG_Long;
			}
			else if (tag instanceof TAG_Float) {
				return TAG_Float;
			}
			else if (tag instanceof TAG_Double) {
				return TAG_Double;
			}
			else if (tag instanceof TAG_Byte_Array) {
				return TAG_Byte_Array;
			}
			else if (tag instanceof TAG_String) {
				return TAG_String;
			}
			else if (tag instanceof TAG_List) {
				return TAG_List;
			}
			else if (tag instanceof TAG_Compound) {
				return TAG_Compound;
			}
			else if (tag instanceof TAG_Int_Array) {
				return TAG_Int_Array;
			}
			return null;
		}
	}
	
	protected String m_name = "";
	protected TAG m_parent = null;
	
	public TAG(String name, TAG parent) {
		m_name = name;
		m_parent = parent;
	}
	
	public String getName() {
		return m_name;
	}
	
	public TAG getParent() {
		return m_parent;
	}
	
	public void setParent(TAG parent) {
		m_parent = parent;
	}
	
	public TAG findTAG(String name) {
		return null;
	}
	
	public abstract Object getValue();
	
	@Override
	public String toString() {
		return getName() + " : " + getValue();
	}
	
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		if (writeName) {
			if (m_name != null && !m_name.isEmpty()) {
				out.writeShort(m_name.length());
				out.write(m_name.getBytes());
			}
			else {
				out.writeShort(0);
			}
		}
	}
	
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		if (readName) {
			int nameLength = in.readShort() & 0xFFFF;
			if (nameLength > 0) {
				byte[] nameBytes = new byte[nameLength];
				in.readFully(nameBytes);
				m_name = new String(nameBytes, "UTF-8");
			}
		}
	}
}
