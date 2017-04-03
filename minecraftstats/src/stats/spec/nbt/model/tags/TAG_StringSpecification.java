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

public class TAG_StringSpecification extends TAGCommonSpecification {

	private String value = "String";
	
	private TAG_String tagString = new TAG_String("", "");
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagString = new TAG_String(name);
		
		Assert.assertEquals(name, tagString.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagString = new TAG_String(name, value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagString = new TAG_String(name, value);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(name, tagString.getName());
		Assert.assertEquals(value, tagString.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagString.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		tagString = new TAG_String(name, value);
		
		tagString.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertValueRead();
	}

	private void assertValueRead() throws IOException {
		Assert.assertEquals(value.length(), inStream.readShort());
		Assert.assertTrue(Arrays.equals(value.getBytes(), readBytesFromStream(value.length())));
	}
	
	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagString.setValue(value);
		
		tagString.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		assertValueRead();
	}
	
	@Test
	public void ShouldReadEmptyValue() throws IOException {
		value = "";
		clearNameInInputStream();
		
		tagString.readFromStream(inStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(0, inStream.available());
	}
	
	@Test
	public void ShouldWriteEmptyValue() throws IOException {
		tagString = new TAG_String(name, "");
		
		tagString.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(0, inStream.available());
	}
	
	@Test
	public void testTAGStringValue() {
		
		TAG_String nbtInt = new TAG_String("", null);
		
		nbtInt.setValue(value);
		
		Assert.assertEquals(value, nbtInt.getValue());
	}
	
	@Override
	public void writeValue() throws IOException {
		outStream.writeShort(value.length());
		outStream.write(value.getBytes());
	}
}
