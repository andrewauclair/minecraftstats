package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Byte_Array extends TAG {

	private Byte[] m_value = null;
	
	public TAG_Byte_Array(String name) {
		super(name);
	}

	public TAG_Byte_Array(String name, Byte[] value) {
		super(name);
		m_value = value;
	}
	
	public Byte[] getValue() {
		return m_value;
	}
	
	public void setValue(Byte[] value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeInt(m_value.length);
		
		for (byte val : m_value) {
			out.writeByte(val);
		}
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		int size = in.readInt();
		
		m_value = new Byte[size];
		
		for (int i = 0; i < size; i++) {
			m_value[i] = in.readByte();
		}
	}
}
