package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG_String;

public class TAG_StringSpecification extends TAGSpecCommon {

	private static final String s_value = "String";
	
	private String value = "String";
	
	private TAG_String tagString = new TAG_String("", "");
	
	@Test
	public void ShouldCreateObject() {
		value = s_value;
		
		tagString = new TAG_String(getName());
		
		assertEquals(getName(), tagString.getName());
		
		tagString = new TAG_String(getName(), value);
		
		assertNameAndValueAreSet();
	}

	@Test
	public void ShouldReadDataFromStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagString.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(getName(), tagString.getName());
		assertEquals(value, tagString.getValue());
	}
	
	@Test
	public void ShouldWriteDataToStream() throws IOException {
		tagString = new TAG_String(getName(), value);
		
		tagString.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertReadString(value);
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagString.readFromStream(inStream, true);
		
		assertEquals("", tagString.getName());
		assertEquals(value, tagString.getValue());
	}

	private void assertValueRead() throws IOException {
		Assert.assertEquals(value.length(), inStream.readShort());
		Assert.assertTrue(Arrays.equals(value.getBytes(), readBytesFromStream(value.length())));
	}
	
	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagString.setValue(value);
		
		tagString.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertValueRead();
	}
	
	@Test
	public void ShouldReadEmptyValue() throws IOException {
		clearValue();
		
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagString.readFromStream(inStream, true);
		
		assertEquals(getName(), tagString.getName());
		assertEquals("", tagString.getValue());
	}

	private void clearValue() {
		value = "";
	}
	
	@Test
	public void ShouldWriteEmptyValue() throws IOException {
		tagString = new TAG_String(getName(), "");
		
		tagString.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(0, inStream.readShort());
		assertEquals(0, inStream.available());
	}
	
	@Test
	public void testTAGStringValue() {
		
		TAG_String nbtInt = new TAG_String("", null);
		
		nbtInt.setValue(value);
		
		assertEquals(value, nbtInt.getValue());
	}
	
	public void writeValue() throws IOException {
		writeString(value);
	}
}
