package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_String;

public class TAG_StringSpecification extends TestCase {

	private static final String s_name = "Test";
	private static final String s_value = "String";
	
	public void testTAGStringRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(s_value.length());
		testOut.write(s_value.getBytes());
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_String nbtInt = new TAG_String("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtInt.getName());
		assertEquals(s_value, nbtInt.getValue());
	}
	
	public void testTAGStringWrite() throws IOException {
		
		TAG_String nbtInt = new TAG_String(s_name, null);
		nbtInt.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtInt.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(s_value.length());
		testOut.write(s_value.getBytes());
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGStringEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeShort(s_value.length());
		testOut.write(s_value.getBytes());
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_String nbtInt = new TAG_String("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		assertEquals("", nbtInt.getName());
		assertEquals(s_value, nbtInt.getValue());
	}

	public void testTAGStringEmptyNameWrite() throws IOException {
		
		TAG_String nbtInt = new TAG_String("", null);
		nbtInt.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtInt.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeShort(s_value.length());
		testOut.write(s_value.getBytes());
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGStringEmptyValueRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(0);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_String nbtInt = new TAG_String("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtInt.getName());
		assertEquals("", nbtInt.getValue());
	}
	
	public void testTAGStringEmptyValueWrite() throws IOException {
		
		TAG_String nbtInt = new TAG_String(s_name, null);
		nbtInt.setValue("");
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtInt.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(0);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGStringValue() {
		
		TAG_String nbtInt = new TAG_String("", null);
		
		nbtInt.setValue(s_value);
		
		assertEquals(s_value, nbtInt.getValue());
	}
	
	public void testTAGStringConstructor() {
		
		TAG_String nbtInt = new TAG_String(s_name, null, s_value);
		
		assertEquals(s_name, nbtInt.getName());
		assertEquals(s_value, nbtInt.getValue());
	}
	
	public void testTAGStringParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_String nbtInt = new TAG_String(s_name, nbtList);
		
		assertEquals(nbtList, nbtInt.getParent());
	}
}
