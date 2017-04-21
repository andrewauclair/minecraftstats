package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import stats.nbt.model.tags.TAG_Byte_Array;

public class TAG_Byte_ArraySpecification extends TAGCommonSpecification {

	private static final Byte[] s_value = { 1, 2, 3, 4, 5 };
	
	private TAG_Byte_Array tagByteArray = new TAG_Byte_Array("");
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagByteArray = new TAG_Byte_Array(name);
		
		assertEquals(name, tagByteArray.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagByteArray = new TAG_Byte_Array(name, s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagByteArray.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(name, tagByteArray.getName());
		assertTrue(Arrays.equals(s_value, tagByteArray.getValue()));
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagByteArray = new TAG_Byte_Array(name, s_value);
		
		tagByteArray.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertValueRead();
	}
	
	private void assertValueRead() throws IOException {
		assertEquals(s_value.length, inStream.readInt());
		for (Byte value : s_value) {
			assertEquals(value.byteValue(), inStream.readByte());
		}
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		
		tagByteArray.readFromStream(inStream, true);
		
		assertEquals("", tagByteArray.getName());
		assertTrue(Arrays.equals(s_value, tagByteArray.getValue()));
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagByteArray.setValue(s_value);
		
		tagByteArray.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertValueRead();
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagByteArray.setValue(s_value);
		
		assertTrue(Arrays.equals(s_value, tagByteArray.getValue()));
	}

	@Override
	public void writeValue() throws IOException {
		outStream.writeInt(s_value.length);
		for (Byte value : s_value) {
			outStream.writeByte(value);
		}
	}
}