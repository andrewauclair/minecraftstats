package stats.nbt.model.tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import stats.nbt.utils.NBTInputStream;
import stats.nbt.utils.NBTOutputStream;

public class TAG_Int_Array extends TAG {

	private Integer[] m_value = null;
	
	public TAG_Int_Array(String name, TAG parent) {
		super(name, parent);
	}

	public TAG_Int_Array(String name, TAG parent, Integer[] value) {
		super(name, parent);
		m_value = value;
	}
	
	@Override
	public Object getValue() {
		return m_value;
	}

	@Override
	public void setValue(Object value) {
		
		if (value instanceof Integer[] ||
			value instanceof int[]) {
			m_value = (Integer[])value;
		}
	}
	
	@Override
	public void writeToStream(DataOutput out) throws IOException {
		
		super.writeToStream(out);
		
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
