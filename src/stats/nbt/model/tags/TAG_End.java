package stats.nbt.model.tags;

import java.io.DataInput;

public class TAG_End extends TAG {

	public TAG_End(TAG parent) {
		super("", parent);
	}

	@Override
	public Object getValue() {
		return null;
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) {
		
	}
}
