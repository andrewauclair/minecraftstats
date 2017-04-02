package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_String extends TAG {

	private String m_value = null;
	
	public TAG_String(String name) {
		super(name);
	}
	
	public TAG_String(String name, String value) {
		super(name);
		m_value = value;
	}

	@Override
	public Object getValue() {
		return m_value;
	}

	public void setValue(String value) {
		m_value = value;
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		out.writeShort(m_value.length());
		
		if (!m_value.isEmpty()) {
			out.write(m_value.getBytes());
		}
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		short length = in.readShort();
		
		if (length > 0) {
			byte[] bytes = new byte[length];
			in.readFully(bytes);
			m_value = new String(bytes, "UTF-8");
		}
		else {
			m_value = "";
		}
	}
}
