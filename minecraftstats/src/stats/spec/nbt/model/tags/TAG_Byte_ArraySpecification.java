package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_List;

public class TAG_Byte_ArraySpecification extends TestCase {

	private static final String s_name = "Test";
	private static final Byte[] s_value = { 1, 2, 3, 4, 5 };
	
	public void testTAGByteArrayRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeInt(s_value.length);
		for (Byte value : s_value) {
			testOut.writeByte(value);
		}
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertTrue(Arrays.equals(s_value, (Object[]) nbtTAG.getValue()));
	}
	
	public void testTAGByteArrayWrite() throws IOException {
		
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array(s_name, null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeInt(s_value.length);
		for (Byte value : s_value) {
			testOut.writeByte(value);
		}
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGByteArrayEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeInt(s_value.length);
		for (Byte value : s_value) {
			testOut.writeByte(value);
		}
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals("", nbtTAG.getName());
		assertTrue(Arrays.equals(s_value, (Object[]) nbtTAG.getValue()));
	}

	public void testTAGByteArrayEmptyNameWrite() throws IOException {
		
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array("", null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeInt(s_value.length);
		for (Byte value : s_value) {
			testOut.writeByte(value);
		}
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGByteArrayValue() {
		
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array("", null);
		
		nbtTAG.setValue(s_value);
		
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGByteArrayConstructor() {
		
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array(s_name, null, s_value);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGByteArrayParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Byte_Array nbtTAG = new TAG_Byte_Array(s_name, nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
