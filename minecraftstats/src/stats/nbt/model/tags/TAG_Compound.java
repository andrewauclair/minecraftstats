package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import stats.nbt.utils.NBTFileHelper;

public class TAG_Compound extends TAG {

	private Map<String, TAG> m_value = new HashMap<String, TAG>();
	
	public TAG_Compound(String name, TAG parent) {
		super(name, parent);
	}

	public TAG_Compound(String name, TAG parent, Map<String, TAG> value) {
		super(name, parent);
		m_value = value;
	}
	
	@Override
	public TAG findTAG(String name) {
		return m_value.get(name);
	}
	
	@Override
	public Object getValue() {
		return m_value;
	}

	@Override
	public void setValue(Object value) {
		
		if (value instanceof Map<?, ?>) {
			m_value = (Map<String, TAG>)value;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		for (TAG tag : m_value.values()) {
			buf.append(tag.toString()).append("\n");
		}
		
		return buf.toString();
	}
	
	@Override
	public void writeToStream(DataOutput out) throws IOException {
		
		super.writeToStream(out);
		
		for (String val : m_value.keySet()) {
			m_value.get(val).writeToStream(out);
		}
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		TAG tag = null;
		
		do {
			tag = NBTFileHelper.readNextTag(in, this);
			m_value.put(tag.getName(), tag);
		} while (!(tag instanceof TAG_End));
	}
}
