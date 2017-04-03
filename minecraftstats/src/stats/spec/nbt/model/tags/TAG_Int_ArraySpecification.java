package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_List;

public class TAG_Int_ArraySpecification extends TAGCommonSpecification {

	private static final String s_name = "Test";
	private static final Integer[] s_value = { 1, 2, 3, 4, 5 };
	
	private TAG_Int_Array tagIntArray = new TAG_Int_Array("");
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagIntArray = new TAG_Int_Array(name);
		
		Assert.assertEquals(name, tagIntArray.getName());
	}
	
	@Test
	public void testTAGIntArrayRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeInt(s_value.length);
		for (Integer value : s_value) {
			testOut.writeInt(value);
		}
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Int_Array nbtTAG = new TAG_Int_Array("");
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertTrue(Arrays.equals(s_value, (Object[]) nbtTAG.getValue()));
	}
	@Test
	public void testTAGIntArrayWrite() throws IOException {
		
		TAG_Int_Array nbtTAG = new TAG_Int_Array(s_name);
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeInt(s_value.length);
		for (Integer value : s_value) {
			testOut.writeInt(value);
		}
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	@Test
	public void testTAGIntArrayEmptyNameRead() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeInt(s_value.length);
		for (Integer value : s_value) {
			testOut.writeInt(value);
		}
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_Int_Array nbtTAG = new TAG_Int_Array("");
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals("", nbtTAG.getName());
		assertTrue(Arrays.equals(s_value, (Object[]) nbtTAG.getValue()));
	}
	@Test
	public void testTAGIntArrayEmptyNameWrite() throws IOException {
		
		TAG_Int_Array nbtTAG = new TAG_Int_Array("");
		nbtTAG.setValue(s_value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeInt(s_value.length);
		for (Integer value : s_value) {
			testOut.writeInt(value);
		}
		
		assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	@Test
	public void testTAGIntArrayValue() {
		
		TAG_Int_Array nbtTAG = new TAG_Int_Array("");
		
		nbtTAG.setValue(s_value);
		
		assertEquals(s_value, nbtTAG.getValue());
	}
	@Test
	public void testTAGIntArrayConstructor() {
		
		TAG_Int_Array nbtTAG = new TAG_Int_Array(s_name, s_value);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(s_value, nbtTAG.getValue());
	}

	@Override
	public void writeValue() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
