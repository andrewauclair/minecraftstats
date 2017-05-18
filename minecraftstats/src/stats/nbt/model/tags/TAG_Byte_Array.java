package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Byte_Array extends TAG {

	private byte[] m_value;
	
	public TAG_Byte_Array(String name, byte[] value) {
		super(name);
		m_value = value;
	}
	
	public byte[] getValue() {
		return m_value;
	}
	
	public void setValue(byte[] value) {
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
		
		m_value = new byte[size];
		in.readFully(m_value);
	}
}
