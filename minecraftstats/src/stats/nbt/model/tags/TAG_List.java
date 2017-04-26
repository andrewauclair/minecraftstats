package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import stats.nbt.utils.NBTFileHelper;

public class TAG_List extends TAG {

	private ArrayList<TAG> m_value = new ArrayList<>();
	private TAG_Type m_type = null;
	
	public TAG_List(String name) {
		super(name);
	}
	
	public TAG_List(String name, ArrayList<TAG> value) {
		super(name);
		
		setValue(value);
	}
	
	public ArrayList<TAG> getValue() {
		return m_value;
	}

	public TAG_Type getType() {
		return m_type;
	}
	
	public void setValue(ArrayList<TAG> value) {
		m_value = value;
		
		if (m_value.size() > 0) {
			m_type = TAG_Type.fromTAG(m_value.get(0));
		}
		else {
			m_type = null;
		}
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {
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
	public void readPayloadFromStream(DataInput in) throws IOException {
		m_type = TAG_Type.fromInt(in.readByte());
		int size = in.readInt();
		
		for (int i = 0; i < size; i++) {
			m_value.add(NBTFileHelper.readTagPayload(in, m_type, false));
		}
	}
}
