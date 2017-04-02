package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Float extends TAG {

	private Float m_value = null;
	
	public TAG_Float(String name) {
		super(name);
	}
	
	public TAG_Float(String name, Float value) {
		super(name);
		m_value = value;
	}

	@Override
	public Object getValue() {
		return m_value;
	}

	public void setValue(Float value) {
		m_value = value;
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		out.writeFloat(m_value);
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		m_value = in.readFloat();
	}
}
