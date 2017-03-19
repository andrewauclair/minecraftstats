package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Double extends TAG {

	private Double m_value = null;
	
	public TAG_Double(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_Double(String name, TAG parent, Double value) {
		super(name, parent);
		m_value = value;
	}

	@Override
	public Object getValue() {
		return m_value;
	}
	
	@Override
	public void writeToStream(DataOutput out) throws IOException {
		
		super.writeToStream(out);
		
		out.writeDouble(m_value);
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		m_value = in.readDouble();
	}
}
