package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

import stats.nbt.utils.NBTFileHelper;

public class TAG_List extends TAG {

	
	public TAG_List(String name, TAG parent) {
		super(name, parent);
	}
	
	public TAG_List(String name, TAG parent, List<TAG> value) {
		super(name, parent);
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		// needs to read the type, size and all the tags
		TAG_Type type = TAG_Type.valueOf(in.readByte());
		int size = in.readInt();
		
		for (int i = 0; i < size; i++) {
			NBTFileHelper.readTagPayload(in, this, type, false);
		}
	}
}
