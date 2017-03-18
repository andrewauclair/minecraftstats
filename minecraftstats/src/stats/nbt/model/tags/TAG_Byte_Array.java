package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.IOException;

public class TAG_Byte_Array extends TAG {

	public TAG_Byte_Array(String name, TAG parent) {
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
		
		int size = in.readInt();
		
		for (int i = 0; i < size; i++) {
			in.readByte();
		}
	}
}
