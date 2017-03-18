package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.IOException;

public class TAG_Long extends TAG {

	private Long m_value = null;
	
	public TAG_Long(String name, TAG parent) {
		super(name, parent);
	}

	public TAG_Long(String name, TAG parent, Long value) {
		super(name, parent);
		m_value = value;
	}

	@Override
	public Object getValue() {
		return m_value;
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		m_value = in.readLong();
	}
}