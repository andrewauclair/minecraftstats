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
import stats.nbt.model.tags.TAG_Byte_Array;
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
	public void ShouldCreateObjectWithNameAndValue() {
		tagIntArray = new TAG_Int_Array(name, s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagIntArray.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(name, tagIntArray.getName());
		assertTrue(Arrays.equals(s_value, tagIntArray.getValue()));
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagIntArray = new TAG_Int_Array(name, s_value);
		
		tagIntArray.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertValueRead();
	}
	
	private void assertValueRead() throws IOException {
		assertEquals(s_value.length, inStream.readInt());
		for (Integer value : s_value) {
			assertEquals(value.intValue(), inStream.readInt());
		}
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		
		tagIntArray.readFromStream(inStream, true);
		
		assertEquals("", tagIntArray.getName());
		assertTrue(Arrays.equals(s_value, tagIntArray.getValue()));
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagIntArray.setValue(s_value);
		
		tagIntArray.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertValueRead();
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagIntArray.setValue(s_value);
		
		assertTrue(Arrays.equals(s_value, tagIntArray.getValue()));
	}

	@Override
	public void writeValue() throws IOException {
		outStream.writeInt(s_value.length);
		for (Integer value : s_value) {
			outStream.writeInt(value);
		}
	}
}
