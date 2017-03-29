package stats.nbt.model.tags.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_Short;

public class TAG_ShortTest extends TestCase {

	private static final String s_name = "Test";
	private static final Short s_value = 15;
	
	public void testTAGShortRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Short nbtShort = new TAG_Short("", null);
		nbtShort.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtShort.getName());
		assertEquals(s_value, nbtShort.getValue());
	}
	
	public void testTAGShortWrite() throws IOException {
		
		TAG_Short nbtShort = new TAG_Short(s_name, null);
		nbtShort.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtShort.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(s_value);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGShortEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeShort(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Short nbtShort = new TAG_Short("", null);
		nbtShort.readFromStream(nbtIn, true);
		
		assertEquals("", nbtShort.getName());
		assertEquals(s_value, nbtShort.getValue());
	}

	public void testTAGShortEmptyNameWrite() throws IOException {
		
		TAG_Short nbtShort = new TAG_Short("", null);
		nbtShort.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtShort.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeShort(s_value);
		
		assertTrue("TAG_Short write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGShortValue() {
		
		TAG_Short nbtShort = new TAG_Short("", null);
		
		nbtShort.setValue(s_value);
		
		assertEquals(s_value, nbtShort.getValue());
	}
	
	public void testTAGShortConstructor() {
		
		TAG_Short nbtShort = new TAG_Short(s_name, null, s_value);
		
		assertEquals(s_name, nbtShort.getName());
		assertEquals(s_value, nbtShort.getValue());
	}
	
	public void testTAGShortParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Short nbtShort = new TAG_Short(s_name, nbtList);
		
		assertEquals(nbtList, nbtShort.getParent());
	}
}
