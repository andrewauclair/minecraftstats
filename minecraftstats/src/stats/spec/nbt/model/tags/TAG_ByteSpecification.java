package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_List;

public class TAG_ByteSpecification extends TestCase {

	private static final String s_name = "Test";
	private static final Byte s_value = 15;
	
	public void testTAGByteRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeByte(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Byte nbtTAG = new TAG_Byte("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGByteWrite() throws IOException {
		
		TAG_Byte nbtTAG = new TAG_Byte(s_name, null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeByte(s_value);
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGByteEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeByte(s_value);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Byte nbtTAG = new TAG_Byte("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals("", nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}

	public void testTAGByteEmptyNameWrite() throws IOException {
		
		TAG_Byte nbtTAG = new TAG_Byte("", null);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeByte(s_value);
		
		assertTrue("TAG_Byte write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGByteValue() {
		
		TAG_Byte nbtTAG = new TAG_Byte("", null);
		
		nbtTAG.setValue(s_value);
		
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGByteConstructor() {
		
		TAG_Byte nbtTAG = new TAG_Byte(s_name, null, s_value);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}
	
	public void testTAGByteParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Byte nbtTAG = new TAG_Byte(s_name, nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
