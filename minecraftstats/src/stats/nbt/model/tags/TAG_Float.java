package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.IOException;

public class TAG_Float extends TAG {

	private Float m_value = null;
	
	public TAG_Float(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_Float(String name, TAG parent, Float value) {
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
		
		m_value = in.readFloat();
	}
}