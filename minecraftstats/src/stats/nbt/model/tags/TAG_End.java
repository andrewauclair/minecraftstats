package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TAG_End extends TAG {

	public TAG_End() {
		super("");
	}

	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
	}
	
	@Override
	public void writePayloadToStream(DataOutput out) throws IOException {	
	}
	
	public void readFromStream(DataInput in, boolean readName) throws IOException {
	}
	
	@Override
	public void readPayloadFromStream(DataInput in) {
	}
}
