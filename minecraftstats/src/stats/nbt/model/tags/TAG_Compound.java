package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import stats.nbt.utils.NBTFileHelper;

public class TAG_Compound extends TAG {

	private Map<String, TAG> m_value = new HashMap<String, TAG>();
	
	public TAG_Compound(String name) {
		super(name);
	}

	public TAG_Compound(String name, Map<String, TAG> value) {
		super(name);
		m_value = value;
	}
	
	@Override
	public TAG findTAG(String name) {
		return m_value.get(name);
	}
	
	// TODO Break down the getValue and setValue into better access functions such as:
	// hasX
	// removeX
	// addX
	
	public Map<String, TAG> getValue() {
		return m_value;
	}

	public void setValue(Map<String, TAG> value) {
		m_value = value;
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
	public void writePayloadToStream(DataOutput out) throws IOException {
		for (String val : m_value.keySet()) {
			out.writeByte(TAG_Type.fromTAG(m_value.get(val)).getValue());
			m_value.get(val).writeToStream(out, true);
		}
		
		out.writeByte(TAG_Type.TAG_End.getValue());
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) throws IOException {
		TAG tag = null;
		
		do {
			tag = NBTFileHelper.readNextTag(in, this);
			m_value.put(tag.getName(), tag);
		} while (!(tag instanceof TAG_End));
	}
}
