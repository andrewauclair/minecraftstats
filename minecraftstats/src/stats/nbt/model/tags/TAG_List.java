package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import stats.nbt.utils.NBTFileHelper;

public class TAG_List extends TAG {

	private ArrayList<TAG> m_value = new ArrayList<>();
	private TAG_Type m_type = null;
	
	public TAG_List(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_List(String name, TAG parent, List<TAG> value) {
		super(name, parent);
		
		// TODO only accept tags that match the first in the list
		if (value.size() > 0) {
			m_type = TAG_Type.fromTAG(value.get(0));
		}
	}

	@Override
	public TAG findTAG(String name) {
		for (TAG tag : m_value) {
			if (tag.getName().equals(name)) {
				return tag;
			}
		}
		return null;
	}
	
	@Override
	public Object getValue() {
		return m_value;
	}

	public void setValue(ArrayList<TAG> value) {
		m_value = value;
		
		if (m_value.size() > 0) {
			m_type = TAG_Type.fromTAG(m_value.get(0));
		}
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		if (m_type != null) {
			out.writeByte(m_type.getValue());
		}
		else {
			out.writeByte(0);
		}
		
		out.writeInt(m_value.size());
		
		for (TAG tag : m_value) {
			tag.writeToStream(out, false);
		}
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		// needs to read the type, size and all the tags
		m_type = TAG_Type.valueOf(in.readByte());
		int size = in.readInt();
		
		for (int i = 0; i < size; i++) {
			m_value.add(NBTFileHelper.readTagPayload(in, this, m_type, false));
		}
	}
}
