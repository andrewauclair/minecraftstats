package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Long extends TAG {

	private Long m_value = null;
	
	public TAG_Long(String name) {
		super(name);
	}

	public TAG_Long(String name, Long value) {
		super(name);
		m_value = value;
	}

	@Override
	public Object getValue() {
		return m_value;
	}

	public void setValue(Long value) {
		m_value = value;
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		out.writeLong(m_value);
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		m_value = in.readLong();
	}
}
