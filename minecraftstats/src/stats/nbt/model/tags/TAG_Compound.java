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
	
	public TAG getTAG(String name) {
		return m_value.get(name);
	}
	
	public void addTAG(TAG value) {
		if (!(value instanceof TAG_End)) {
			m_value.put(value.getName(), value);
		}
	}
	
	public void removeTAG(String name) {
		m_value.remove(name);
	}
	
	public boolean hasTAG(String name) {
		return m_value.containsKey(name);
	}
	
	public int count() {
		return m_value.keySet().size();
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
			if (!(tag instanceof TAG_End)) {
				m_value.put(tag.getName(), tag);
			}
		} while (!(tag instanceof TAG_End));
	}
}
