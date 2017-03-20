package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Byte extends TAG {

	private Byte m_value = null;
	
	public TAG_Byte(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_Byte(String name, TAG parent, Byte value) {
		super(name, parent);
		m_value = value;
	}

	@Override
	public Object getValue() {
		return m_value;
	}

	public void setValue(Byte value) {
		m_value = value;
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		out.writeByte(m_value);
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		m_value = in.readByte();
	}
}
