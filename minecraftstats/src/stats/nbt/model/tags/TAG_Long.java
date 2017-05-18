package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Long extends TAG {

	private long m_value;
	
	public TAG_Long(String name, long value) {
		super(name);
		m_value = value;
	}

	public long getValue() {
		return m_value;
	}

	public void setValue(long value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeLong(m_value);
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_value = in.readLong();
	}
}
