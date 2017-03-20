package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Byte_Array extends TAG {

	private Byte[] m_value = null;
	
	public TAG_Byte_Array(String name, TAG parent) {
		super(name, parent);
	}

	public TAG_Byte_Array(String name, TAG parent, Byte[] value) {
		super(name, parent);
		m_value = value;
	}
	@Override
	public Object getValue() {
		return m_value;
	}
	
	public void setValue(Byte[] value) {
		m_value = value;
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		out.writeInt(m_value.length);
		
		for (byte val : m_value) {
			out.writeByte(val);
		}
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		int size = in.readInt();
		
		m_value = new Byte[size];
		
		for (int i = 0; i < size; i++) {
			m_value[i] = in.readByte();
		}
	}
}
