package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Byte extends TAG {

	private byte m_value = 0;
	
	public TAG_Byte(String name) {
		super(name);
	}
	
	public TAG_Byte(String name, byte value) {
		super(name);
		m_value = value;
	}

	public byte getValue() {
		return m_value;
	}

	public void setValue(Byte value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeByte(m_value);
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_value = in.readByte();
	}
}
