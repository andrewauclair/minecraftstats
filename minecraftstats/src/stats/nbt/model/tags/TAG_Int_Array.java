package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import stats.nbt.utils.NBTInputStream;
import stats.nbt.utils.NBTOutputStream;

public class TAG_Int_Array extends TAG {

	private Integer[] m_value = null;
	
	public TAG_Int_Array(String name) {
		super(name);
	}

	public TAG_Int_Array(String name, Integer[] value) {
		super(name);
		m_value = value;
	}
	
	@Override
	public Object getValue() {
		return m_value;
	}

	public void setValue(Integer[] value) {
		m_value = value;
	}
	
	@Override
	public void writeToStream(DataOutput out, boolean writeName) throws IOException {
		
		super.writeToStream(out, writeName);
		
		out.writeInt(m_value.length);
		
		for (int val : m_value) {
			out.writeInt(val);
		}
	}
	
	@Override
	public void readFromStream(DataInput in, boolean readName) throws IOException {
		
		super.readFromStream(in, readName);
		
		int size = in.readInt();
		
		m_value = new Integer[size];
		
		for (int i = 0; i < size; i++) {
			m_value[i] = in.readInt();
		}
	}
}
