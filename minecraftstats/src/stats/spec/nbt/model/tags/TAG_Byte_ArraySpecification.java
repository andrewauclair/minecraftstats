package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Byte_Array;

public class TAG_Byte_ArraySpecification extends TAGSpecCommon {

	private static final byte[] s_value = { 1, 2, 3, 4, 5 };
	
	private TAG_Byte_Array tagByteArray;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		tagByteArray = new TAG_Byte_Array("", new byte[0]);
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagByteArray = new TAG_Byte_Array(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagByteArray.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(getName(), tagByteArray.getName());
		assertTrue(Arrays.equals(s_value, tagByteArray.getValue()));
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagByteArray = new TAG_Byte_Array(getName(), s_value);
		
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
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagByteArray.readFromStream(inStream, true);
		
		assertEquals("", tagByteArray.getName());
		assertTrue(Arrays.equals(s_value, tagByteArray.getValue()));
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
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

	public void writeValue() throws IOException {
		outStream.writeInt(s_value.length);
		for (Byte value : s_value) {
			outStream.writeByte(value);
		}
	}
}
