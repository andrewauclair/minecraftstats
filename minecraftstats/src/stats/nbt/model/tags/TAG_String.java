package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.IOException;

public class TAG_String extends TAG {

	private String m_value = null;
	
	public TAG_String(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_String(String name, TAG parent, String value) {
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
		
		short length = in.readShort();
		
		if (length > 0) {
			byte[] bytes = new byte[length];
			in.readFully(bytes);
			m_value = new String(bytes, "UTF-8");
		}
	}
}
