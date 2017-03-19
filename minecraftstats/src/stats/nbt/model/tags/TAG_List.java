package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import stats.nbt.utils.NBTFileHelper;

public class TAG_List extends TAG {

	private ArrayList<TAG> m_value = new ArrayList<>();
	
	public TAG_List(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_List(String name, TAG parent, List<TAG> value) {
		super(name, parent);
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
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		// needs to read the type, size and all the tags
		TAG_Type type = TAG_Type.valueOf(in.readByte());
		int size = in.readInt();
		
		for (int i = 0; i < size; i++) {
			m_value.add(NBTFileHelper.readTagPayload(in, this, type, false));
		}
	}
}
