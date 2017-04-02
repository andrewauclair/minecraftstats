package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_End extends TAG {

	public TAG_End() {
		super("");
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) {
		
	}
}
