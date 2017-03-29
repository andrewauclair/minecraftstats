package stats.spec.nbt.model.tags;

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

import junit.framework.TestCase;
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

public class TAG_CompoundSpecification extends TestCase {

	private static final String s_name = "Test";
	
	public void testTAGCompoundRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeByte(0);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Compound nbtTAG = new TAG_Compound("", null);
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
	}

	public void testTAGCompoundWrite_TAGByte() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Byte", new TAG_Byte("TAG_Byte", nbtTAG, (byte) 0));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(1);
		testOut.writeShort("TAG_Byte".length());
		testOut.write("TAG_Byte".getBytes());
		testOut.writeByte(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGShort() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Short", new TAG_Short("TAG_Short", nbtTAG, (short) 0));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(2);
		testOut.writeShort("TAG_Short".length());
		testOut.write("TAG_Short".getBytes());
		testOut.writeShort(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGInt() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Int", new TAG_Int("TAG_Int", nbtTAG, 0));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(3);
		testOut.writeShort("TAG_Int".length());
		testOut.write("TAG_Int".getBytes());
		testOut.writeInt(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}

	public void testTAGCompoundWrite_TAGLong() throws IOException {
	
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Long", new TAG_Long("TAG_Long", nbtTAG, (long) 0));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(4);
		testOut.writeShort("TAG_Long".length());
		testOut.write("TAG_Long".getBytes());
		testOut.writeLong(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGFloat() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Float", new TAG_Float("TAG_Float", nbtTAG, 0f));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(5);
		testOut.writeShort("TAG_Float".length());
		testOut.write("TAG_Float".getBytes());
		testOut.writeFloat(0.0f);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGDouble() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Double", new TAG_Double("TAG_Double", nbtTAG, 0.0));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(6);
		testOut.writeShort("TAG_Double".length());
		testOut.write("TAG_Double".getBytes());
		testOut.writeDouble(0.0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGByteArray() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Byte_Array", new TAG_Byte_Array("TAG_Byte_Array", nbtTAG, new Byte[] {}));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(7);
		testOut.writeShort("TAG_Byte_Array".length());
		testOut.write("TAG_Byte_Array".getBytes());
		testOut.writeInt(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGString() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_String", new TAG_String("TAG_String", nbtTAG, s_name));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(8);
		testOut.writeShort("TAG_String".length());
		testOut.write("TAG_String".getBytes());
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGList() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_List", new TAG_List("TAG_List", nbtTAG));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(9);
		testOut.writeShort("TAG_List".length());
		testOut.write("TAG_List".getBytes());
		testOut.writeByte(0);
		testOut.writeInt(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGCompound() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Compound", new TAG_Compound("TAG_Compound", nbtTAG));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(10);
		testOut.writeShort("TAG_Compound".length());
		testOut.write("TAG_Compound".getBytes());
		testOut.writeByte(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGCompoundWrite_TAGIntArray() throws IOException {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Int_Array", new TAG_Int_Array("TAG_Int_Array", nbtTAG, new Integer[] {}));
		
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		
		testOut.writeByte(11);
		testOut.writeShort("TAG_Int_Array".length());
		testOut.write("TAG_Int_Array".getBytes());
		testOut.writeInt(0);
		
		testOut.writeByte(0);
		
		byte[] a = expectedStream.toByteArray();
		byte[] b = byteStream.toByteArray();
		
		assertTrue("TAG_Compound write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
//	
//	public void testTAGCompoundEmptyNameRead() throws IOException {
//		
//		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
//		DataOutputStream testOut = new DataOutputStream(expectedStream);
//		
//		testOut.writeShort(0);
//		testOut.writeInt(s_value);
//		
//		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
//		DataInputStream nbtIn = new DataInputStream(byteStream);
//		
//		TAG_Compound nbtTAG = new TAG_Compound("", null);
//		nbtTAG.readFromStream(nbtIn, true);
//		
//		assertEquals("", nbtTAG.getName());
//		assertEquals(s_value, nbtTAG.getValue());
//	}
//
//	public void testTAGCompoundEmptyNameWrite() throws IOException {
//		
//		TAG_Compound nbtTAG = new TAG_Compound("", null);
//		nbtTAG.setValue(s_value);
//		
//		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//		DataOutputStream nbtOut = new DataOutputStream(byteStream);
//		nbtTAG.writeToStream(nbtOut);
//		
//		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
//		DataOutputStream testOut = new DataOutputStream(expectedStream);
//		
//		testOut.writeShort(0);
//		testOut.writeInt(s_value);
//		
//		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
//	}
	
	public void testTAGCompoundValue() {
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null);
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Byte", new TAG_Byte("TAG_Byte", null));
		value.put("TAG_Short", new TAG_Short("TAG_Short", null));
		value.put("TAG_Int", new TAG_Int("TAG_Int", null));
		value.put("TAG_Long", new TAG_Long("TAG_Long", null));
		value.put("TAG_Float", new TAG_Float("TAG_Float", null));
		value.put("TAG_Double", new TAG_Double("TAG_Double", null));
		value.put("TAG_Byte_Array", new TAG_Byte_Array("TAG_Byte_Array", null));
		value.put("TAG_String", new TAG_String("TAG_String", null));
		value.put("TAG_List", new TAG_List("TAG_List", null));
		value.put("TAG_Compound", new TAG_Compound("TAG_Compound", null));
		value.put("TAG_Int_Array", new TAG_Int_Array("TAG_Int_Array", null));
		
		nbtTAG.setValue(value);
		
		assertEquals(s_name, nbtTAG.getName());
		
		assertEquals("TAG_Byte", nbtTAG.findTAG("TAG_Byte").getName());
		assertEquals("TAG_Short", nbtTAG.findTAG("TAG_Short").getName());
		assertEquals("TAG_Int", nbtTAG.findTAG("TAG_Int").getName());
		assertEquals("TAG_Long", nbtTAG.findTAG("TAG_Long").getName());
		assertEquals("TAG_Float", nbtTAG.findTAG("TAG_Float").getName());
		assertEquals("TAG_Double", nbtTAG.findTAG("TAG_Double").getName());
		assertEquals("TAG_Byte_Array", nbtTAG.findTAG("TAG_Byte_Array").getName());
		assertEquals("TAG_String", nbtTAG.findTAG("TAG_String").getName());
		assertEquals("TAG_List", nbtTAG.findTAG("TAG_List").getName());
		assertEquals("TAG_Compound", nbtTAG.findTAG("TAG_Compound").getName());
		assertEquals("TAG_Int_Array", nbtTAG.findTAG("TAG_Int_Array").getName());
		
		assertTrue(nbtTAG.findTAG("TAG_Byte").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Short").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Int").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Long").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Float").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Double").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Byte_Array").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_String").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_List").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Compound").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Int_Array").getParent() == nbtTAG);
	}
	
	public void testTAGCompoundConstructor() {
		
		Map<String, TAG> value = new HashMap<String, TAG>();
		value.put("TAG_Byte", new TAG_Byte("TAG_Byte", null));
		value.put("TAG_Short", new TAG_Short("TAG_Short", null));
		value.put("TAG_Int", new TAG_Int("TAG_Int", null));
		value.put("TAG_Long", new TAG_Long("TAG_Long", null));
		value.put("TAG_Float", new TAG_Float("TAG_Float", null));
		value.put("TAG_Double", new TAG_Double("TAG_Double", null));
		value.put("TAG_Byte_Array", new TAG_Byte_Array("TAG_Byte_Array", null));
		value.put("TAG_String", new TAG_String("TAG_String", null));
		value.put("TAG_List", new TAG_List("TAG_List", null));
		value.put("TAG_Compound", new TAG_Compound("TAG_Compound", null));
		value.put("TAG_Int_Array", new TAG_Int_Array("TAG_Int_Array", null));
		
		TAG_Compound nbtTAG = new TAG_Compound(s_name, null, value);
		
		assertEquals(s_name, nbtTAG.getName());
		
		assertEquals("TAG_Byte", nbtTAG.findTAG("TAG_Byte").getName());
		assertEquals("TAG_Short", nbtTAG.findTAG("TAG_Short").getName());
		assertEquals("TAG_Int", nbtTAG.findTAG("TAG_Int").getName());
		assertEquals("TAG_Long", nbtTAG.findTAG("TAG_Long").getName());
		assertEquals("TAG_Float", nbtTAG.findTAG("TAG_Float").getName());
		assertEquals("TAG_Double", nbtTAG.findTAG("TAG_Double").getName());
		assertEquals("TAG_Byte_Array", nbtTAG.findTAG("TAG_Byte_Array").getName());
		assertEquals("TAG_String", nbtTAG.findTAG("TAG_String").getName());
		assertEquals("TAG_List", nbtTAG.findTAG("TAG_List").getName());
		assertEquals("TAG_Compound", nbtTAG.findTAG("TAG_Compound").getName());
		assertEquals("TAG_Int_Array", nbtTAG.findTAG("TAG_Int_Array").getName());
		
		assertTrue(nbtTAG.findTAG("TAG_Byte").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Short").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Int").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Long").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Float").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Double").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Byte_Array").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_String").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_List").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Compound").getParent() == nbtTAG);
		assertTrue(nbtTAG.findTAG("TAG_Int_Array").getParent() == nbtTAG);
	}
	
	public void testTAGCompoundTAGNotFound() {
		
		TAG_Compound nbtTAG = new TAG_Compound("", null);
		
		assertNull(nbtTAG.findTAG("TAG"));
	}
	
	public void testTAGCompoundParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_Compound nbtTAG = new TAG_Compound(s_name, nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
