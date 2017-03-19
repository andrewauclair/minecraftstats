package stats.nbt.model.tags.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_Double;
import stats.nbt.model.tags.TAG_List;

public class TAG_DoubleTest extends TestCase {

	private static final String s_name = "Test";
	private static final Double s_value = 15.0;
	
	public void testTAGDoubleRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeDouble(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Double nbtTAG = new TAG_Double("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGDoubleWrite() throws IOException {
		
		TAG_Double nbtTAG = new TAG_Double(s_name, null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeDouble(s_value);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGDoubleEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeDouble(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Double nbtTAG = new TAG_Double("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals("", nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}

	public void testTAGDoubleEmptyNameWrite() throws IOException {
		
		TAG_Double nbtTAG = new TAG_Double("", null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeDouble(s_value);
		
		assertTrue("TAG_Double write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGDoubleValue() {
		
		TAG_Double nbtTAG = new TAG_Double("", null);
		
		nbtTAG.setValue(s_value);
		
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGDoubleConstructor() {
		
		TAG_Double nbtTAG = new TAG_Double(s_name, null, s_value);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGDoubleParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Double nbtTAG = new TAG_Double(s_name, nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
