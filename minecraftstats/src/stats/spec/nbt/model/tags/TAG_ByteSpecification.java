package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Byte;

public class TAG_ByteSpecification extends TAGCommonSpecification {

	private static final byte s_value = 15;
	
	private TAG_Byte tagByte = new TAG_Byte("", (byte)0);
	
	@Test
	public void ShouldCreateObject() {
		tagByte = new TAG_Byte(getName());
		
		Assert.assertEquals(getName(), tagByte.getName());
		
		tagByte = new TAG_Byte(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromStream() throws IOException {
		writeName();
		writeByte(s_value);
		
		createInputStreamFromOutputStream();
		
		tagByte.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(getName(), tagByte.getName());
		Assert.assertEquals(s_value, tagByte.getValue());
	}
	
	@Test
	public void ShouldWriteDataToStream() throws IOException {
		tagByte = new TAG_Byte(getName(), s_value);
		
		tagByte.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value, inStream.readByte());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeByte(s_value);
		
		createInputStreamFromOutputStream();
		
		tagByte.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagByte.getName());
		Assert.assertEquals(s_value, tagByte.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagByte.setValue(s_value);
		
		tagByte.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value, inStream.readByte());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagByte.setValue(s_value);
		
		Assert.assertEquals(s_value, tagByte.getValue());
	}
}
