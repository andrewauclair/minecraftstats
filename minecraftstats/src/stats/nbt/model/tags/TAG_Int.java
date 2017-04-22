package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Int extends TAG {

	private Integer m_value = null;
	
	public TAG_Int(String name) {
		super(name);
	}
	
	public TAG_Int(String name, Integer value) {
		super(name);
		m_value = value;
	}
	
	public Integer getValue() {
		return m_value;
	}

	public void setValue(Integer value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeInt(m_value);
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_value = in.readInt();
	}

}
