package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Byte;

public class TAG_ByteSpecification extends TAGCommonSpecification {

	private static final Byte s_value = 15;
	
	private TAG_Byte tagByte = new TAG_Byte("", (byte)0);
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagByte = new TAG_Byte(name);
		
		Assert.assertEquals(name, tagByte.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagByte = new TAG_Byte(name, s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagByte.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(name, tagByte.getName());
		Assert.assertEquals(s_value, tagByte.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagByte = new TAG_Byte(name, s_value);
		
		tagByte.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value.byteValue(), inStream.readByte());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		
		tagByte.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagByte.getName());
		Assert.assertEquals(s_value, tagByte.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagByte.setValue(s_value);
		
		tagByte.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value.byteValue(), inStream.readByte());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagByte.setValue(s_value);
		
		Assert.assertEquals(s_value, tagByte.getValue());
	}

	@Override
	public void writeValue() throws IOException {
		outStream.writeByte(s_value);
	}
}
