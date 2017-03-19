package stats.nbt.model.tags.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_List;

public class TAG_FloatTest extends TestCase {

	private static final String s_name = "Test";
	private static final Float s_value = 15.0f;
	
	public void testTAGFloatRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeFloat(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Float nbtInt = new TAG_Float("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtInt.getName());
		assertEquals(s_value, nbtInt.getValue());
	}
	
	public void testTAGFloatWrite() throws IOException {
		
		TAG_Float nbtInt = new TAG_Float(s_name, null);
		nbtInt.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtInt.writeToStream(nbtOut);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeFloat(s_value);
		
		assertTrue("TAG_Float write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGFloatEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeFloat(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Float nbtInt = new TAG_Float("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		assertEquals("", nbtInt.getName());
		assertEquals(s_value, nbtInt.getValue());
	}

	public void testTAGFloatEmptyNameWrite() throws IOException {
		
		TAG_Float nbtInt = new TAG_Float("", null);
		nbtInt.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtInt.writeToStream(nbtOut);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeFloat(s_value);
		
		assertTrue("TAG_Float write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGFloatValue() {
		
		TAG_Float nbtInt = new TAG_Float("", null);
		
		nbtInt.setValue(s_value);
		
		assertEquals(s_value, nbtInt.getValue());
	}
	
	public void testTAGFloatConstructor() {
		
		TAG_Float nbtInt = new TAG_Float(s_name, null, s_value);
		
		assertEquals(s_name, nbtInt.getName());
		assertEquals(s_value, nbtInt.getValue());
	}
	
	public void testTAGFloatParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Float nbtInt = new TAG_Float(s_name, nbtList);
		
		assertEquals(nbtList, nbtInt.getParent());
	}
}
