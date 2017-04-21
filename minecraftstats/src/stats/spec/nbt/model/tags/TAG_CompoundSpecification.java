package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

// test nested compounds
// test nested lists
// test with each type of tag

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Double;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;
import stats.nbt.model.tags.TAG.TAG_Type;

public class TAG_CompoundSpecification extends TAGCommonSpecification {

	private TAG_Compound tagCompound = new TAG_Compound("");
	Map<String, TAG> values = new HashMap<String, TAG>();
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		
		values = new HashMap<String, TAG>();
		values.put("value1", new TAG_Byte("value1", (byte)0));
		values.put("value2", new TAG_Byte("value2", (byte)0));
	}
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagCompound = new TAG_Compound(name);
		
		assertEquals(name, tagCompound.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValues() {
		tagCompound = new TAG_Compound(name, values);
		
		assertEquals(name, tagCompound.getName());
		assertEquals(values, tagCompound.getValue());
	}
	
	@Test
	public void ShouldReadValuesFromStream() throws IOException {
		
		outStream.writeShort(name.length());
		outStream.write(name.getBytes());
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
		
		assertEquals(name, tagCompound.getName());
		assertEquals(3, tagCompound.getValue().size());
	}
	
	@Test
	public void ShouldWriteValuesToStream() throws IOException {
		tagCompound = new TAG_Compound(name);
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
	
	private void assertReadString(String expected) throws IOException {
		assertEquals(expected.length(), inStream.readShort());
		assertTrue(Arrays.equals(expected.getBytes(), readBytesFromStream(expected.length())));
	}
	
	@Test
	public void ShouldReturnValuesForFindTag() {
		tagCompound = new TAG_Compound(name, values);
		
		assertEquals(null, tagCompound.findTAG("nothing"));
		assertEquals(values.get("value1"), tagCompound.findTAG("value1"));
	}
	@Override
	public void writeValue() throws IOException {
	}
}
