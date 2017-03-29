package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_List;

public class TAG_IntSpecification extends TestCase {

	private static final String s_name = "Test";
	private static final Integer s_value = 15;
	
	public void testTAGIntRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeInt(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Int nbtTAG = new TAG_Int("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGIntWrite() throws IOException {
		
		TAG_Int nbtTAG = new TAG_Int(s_name, null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeInt(s_value);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGIntEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeInt(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Int nbtTAG = new TAG_Int("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals("", nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}

	public void testTAGIntEmptyNameWrite() throws IOException {
		
		TAG_Int nbtTAG = new TAG_Int("", null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeInt(s_value);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGIntValue() {
		
		TAG_Int nbtTAG = new TAG_Int("", null);
		
		nbtTAG.setValue(s_value);
		
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGIntConstructor() {
		
		TAG_Int nbtTAG = new TAG_Int(s_name, null, s_value);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGIntParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Int nbtTAG = new TAG_Int(s_name, nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
