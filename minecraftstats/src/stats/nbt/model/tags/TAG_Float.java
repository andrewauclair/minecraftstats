package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Float extends TAG {

	private float m_value = 0f;
	
	public TAG_Float(String name) {
		super(name);
	}
	
	public TAG_Float(String name, Float value) {
		super(name);
		m_value = value;
	}

	public float getValue() {
		return m_value;
	}

	public void setValue(float value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeFloat(m_value);
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_value = in.readFloat();
	}
}
