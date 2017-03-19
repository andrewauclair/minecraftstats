package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Byte_Array extends TAG {

	private byte[] m_value = null;
	
	public TAG_Byte_Array(String name, TAG parent) {
		super(name, parent);
	}

	@Override
	public Object getValue() {
		return m_value;
	}
	
	@Override
	public void setValue(Object value) {
		
		if (value instanceof Byte[] ||
			value instanceof byte[]) {
			m_value = (byte[])value;
		}
	}
	
	@Override
	public void writeToStream(DataOutput out) throws IOException {
		
		super.writeToStream(out);
		
		out.writeInt(m_value.length);
		
		for (byte val : m_value) {
			out.writeByte(val);
		}
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		int size = in.readInt();
		
		m_value = new byte[size];
		
		for (int i = 0; i < size; i++) {
			m_value[i] = in.readByte();
		}
	}
}
