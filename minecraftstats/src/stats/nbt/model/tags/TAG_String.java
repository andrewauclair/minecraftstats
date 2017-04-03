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

	public String getValue() {
		return m_value;
	}

	public void setValue(String value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeShort(m_value.length());
		
		if (!m_value.isEmpty()) {
			out.write(m_value.getBytes());
		}
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
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
