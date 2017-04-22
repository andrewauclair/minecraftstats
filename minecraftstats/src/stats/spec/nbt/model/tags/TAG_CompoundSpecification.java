package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;

public class TAG_CompoundSpecification extends TAGCommonSpecification {

	private TAG_Compound tagCompound = new TAG_Compound("");
	Map<String, TAG> values = new HashMap<String, TAG>();
	
	@Before
	public void setUp() throws IOException {
		super.setup();
		
		values = new HashMap<String, TAG>();
		values.put("value1", new TAG_Byte("value1", (byte)0));
		values.put("value2", new TAG_Byte("value2", (byte)0));
	}
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagCompound = new TAG_Compound(getName());
		
		assertEquals(getName(), tagCompound.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValues() {
		tagCompound = new TAG_Compound(getName(), values);
		
		assertEquals(getName(), tagCompound.getName());
		assertEquals(values, tagCompound.getValue());
	}
	
	@Test
	public void ShouldReadValuesFromStream() throws IOException {
		
		outStream.writeShort(getName().length());
		outStream.write(getName().getBytes());
		outStream.writeByte(1);
		outStream.writeShort("value1".length());
		outStream.write("value1".getBytes());
		outStream.writeByte(0);
		outStream.writeByte(1);
		outStream.writeShort("value2".length());
		outStream.write("value2".getBytes());
		outStream.writeByte(0);
		outStream.writeByte(0);
		
		createInputStreamFromOutputStream();
		
		tagCompound.readFromStream(inStream, true);
		
		assertEquals(getName(), tagCompound.getName());
		assertEquals(3, tagCompound.getValue().size());
	}
	
	@Test
	public void ShouldWriteValuesToStream() throws IOException {
		tagCompound = new TAG_Compound(getName());
		tagCompound.setValue(values);
		
		tagCompound.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(1, inStream.readByte());
		assertReadString("value2");
		assertEquals(0, inStream.readByte());
		assertEquals(1, inStream.readByte());
		assertReadString("value1");
		assertEquals(0, inStream.readByte());
		assertEquals(0, inStream.readByte());
	}
	
	@Test
	public void ShouldReturnValuesForFindTag() {
		tagCompound = new TAG_Compound(getName(), values);
		
		assertEquals(null, tagCompound.findTAG("nothing"));
		assertEquals(values.get("value1"), tagCompound.findTAG("value1"));
	}
}
