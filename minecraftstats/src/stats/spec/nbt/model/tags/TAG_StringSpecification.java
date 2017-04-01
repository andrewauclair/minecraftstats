package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_String;

public class TAG_StringSpecification {

	private static final String s_name = "Test";
	private static final String s_value = "String";
	
	@Test
	public void ShouldReadAllValuesFromInputStream() throws IOException {

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
		
		Assert.assertEquals(s_name, nbtInt.getName());
		Assert.assertEquals(s_value, nbtInt.getValue());
	}
	
	@Test
	public void ShouldWriteAllValuesToOutputStream() throws IOException {
		
		// Given
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(s_value.length());
		testOut.write(s_value.getBytes());
		
		TAG_String nbtInt = new TAG_String(s_name, null);
		nbtInt.setValue(s_value);
		
		// When
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtInt.writeToStream(nbtOut, true);
		
		// Then
		ByteArrayInputStream inputStream = new ByteArrayInputStream(byteStream.toByteArray());
		DataInputStream in = new DataInputStream(inputStream);
		
		Assert.assertEquals(s_name.length(), in.readShort());
		//Assert.assertEquals(s_name.getBytes(), in.read());
		
		Assert.assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void ShouldReadValuesWithEmptyName() throws IOException {
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(0);
		testOut.writeShort(s_value.length());
		testOut.write(s_value.getBytes());
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_String nbtInt = new TAG_String("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		Assert.assertEquals("", nbtInt.getName());
		Assert.assertEquals(s_value, nbtInt.getValue());
	}

	@Test
	public void ShouldWriteValuesWithEmptyName() throws IOException {
		
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
		
		Assert.assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	@Test
	public void ShouldReadValueWithEmptyString() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeShort(0);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_String nbtInt = new TAG_String("", null);
		nbtInt.readFromStream(nbtIn, true);
		
		Assert.assertEquals(s_name, nbtInt.getName());
		Assert.assertEquals("", nbtInt.getValue());
	}
	
	@Test
	public void ShouldWriteValueWithEmptyString() throws IOException {
		
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
		
		Assert.assertTrue("TAG_Int write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	@Test
	public void ShouldAllowSetAndGetOfStringValue() {
		
		// Given
		TAG_String nbtInt = new TAG_String("", null);
		
		// When
		nbtInt.setValue(s_value);
		
		// Then
		Assert.assertEquals(s_value, nbtInt.getValue());
	}
	
	@Test
	public void ShouldConstructObjectWithNameParentAndValue() {
		
		// Given
		TAG_List nbtList = new TAG_List("", null);
		TAG_String nbtInt = new TAG_String(s_name, nbtList, s_value);
		
		// Then
		Assert.assertEquals(s_name, nbtInt.getName());
		Assert.assertEquals(nbtList, nbtInt.getParent());
		Assert.assertEquals(s_value, nbtInt.getValue());
	}
}
