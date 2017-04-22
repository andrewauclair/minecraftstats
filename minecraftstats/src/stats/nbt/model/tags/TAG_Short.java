package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Short extends TAG {

	private short m_value = 0;
	
	public TAG_Short(String name) {
		super(name);
	}

	public TAG_Short(String name, short value) {
		super(name);
		m_value = value;
	}

	public short getValue() {
		return m_value;
	}

	public void setValue(short value) {
		m_value = value;
	}

	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeShort(m_value);
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_value = in.readShort();
	}
}
