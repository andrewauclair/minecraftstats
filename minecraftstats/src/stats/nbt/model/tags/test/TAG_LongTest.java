package stats.nbt.model.tags.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_Long;

public class TAG_LongTest extends TestCase {

	private static final String s_name = "Test";
	private static final Long s_value = 15L;
	
	public void testTAGLongRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeLong(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Long nbtTAG = new TAG_Long("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGLongWrite() throws IOException {
		
		TAG_Long nbtTAG = new TAG_Long(s_name, null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeLong(s_value);
		
		assertTrue("TAG_Long write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGLongEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeLong(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Long nbtTAG = new TAG_Long("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals("", nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}

	public void testTAGLongEmptyNameWrite() throws IOException {
		
		TAG_Long nbtTAG = new TAG_Long("", null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeLong(s_value);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGLongValue() {
		
		TAG_Long nbtTAG = new TAG_Long("", null);
		
		nbtTAG.setValue(s_value);
		
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGLongConstructor() {
		
		TAG_Long nbtTAG = new TAG_Long(s_name, null, s_value);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGLongParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Long nbtTAG = new TAG_Long(s_name, nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
