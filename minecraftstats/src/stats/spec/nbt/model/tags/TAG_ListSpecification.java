package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_List;

// Test list with an arraylist of each tag, that will be 11 test?
// nested lists, nested with compound

// need to do some form of full structure test with all of the tags

public class TAG_ListSpecification extends TAGCommonSpecification {

	private TAG_List tagList = new TAG_List("");
	private ArrayList<TAG> values = new ArrayList<>();
	
	private byte byteValue1 = 15;
	private byte byteValue2 = 20;
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		
		values = new ArrayList<>();
		values.add(new TAG_Byte("byte_1", byteValue1));
		values.add(new TAG_Byte("byte_2", byteValue2));
	}
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagList = new TAG_List(name);
		
		assertEquals(name, tagList.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValues() {
		tagList = new TAG_List(name, values);
		
		assertEquals(name, tagList.getName());
		assertEquals(values, tagList.getValue());
	}
	
	@Test
	public void ShouldReadValuesFromStream() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(name.length());
		testOut.write(name.getBytes());
		testOut.writeByte(1);
		testOut.writeInt(1);
		testOut.writeByte(0);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_List nbtTAG = new TAG_List("");
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(name, nbtTAG.getName());
		assertEquals(1, ((ArrayList<TAG>)nbtTAG.getValue()).size());
	}
	
	@Test
	public void ShouldWriteValuesToStream() throws IOException {
		
		TAG_List nbtTAG = new TAG_List(name);
		ArrayList<TAG> value = new ArrayList<>();
		value.add(new TAG_Byte("", (byte) 0));
	
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(name.length());
		testOut.write(name.getBytes());
		testOut.writeByte(1);
		testOut.writeInt(1);
		testOut.writeByte(0);
		
		assertTrue("TAG_List write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}

	@Test
	public void ShouldWriteType0WhenEmpty() throws IOException {
		
		TAG_List nbtTAG = new TAG_List(name);
		
		nbtTAG.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(0, inStream.readByte());
		assertEquals(0, inStream.readInt());
		assertEquals(0, inStream.available());
	}
	
	@Test
	public void ShouldSetTypeToNullWithEmptyValue() {
		ArrayList<TAG> value = new ArrayList<>();
		value.add(new TAG_Byte("", (byte) 0));
		
		tagList.setValue(value);
		
		assertEquals(TAG_Type.TAG_Byte, tagList.getType());
		
		tagList.setValue(new ArrayList<>());
		
		assertEquals(null, tagList.getType());
	}
	
	@Override
	public void writeValue() throws IOException {
	}
}
