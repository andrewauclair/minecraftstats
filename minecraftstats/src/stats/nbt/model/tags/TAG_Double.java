package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_Double extends TAG {

	private Double m_value = null;
	
	public TAG_Double(String name) {
		super(name);
	}
	
	public TAG_Double(String name, Double value) {
		super(name);
		m_value = value;
	}

	public Double getValue() {
		return m_value;
	}

	public void setValue(Double value) {
		m_value = value;
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
		out.writeDouble(m_value);
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_value = in.readDouble();
	}
}
