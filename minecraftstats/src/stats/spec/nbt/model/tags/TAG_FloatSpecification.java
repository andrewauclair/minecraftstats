package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Float;

public class TAG_FloatSpecification extends TAGSpecCommon {

	private static final Float s_value = 15.0f;

	private TAG_Float tagFloat = new TAG_Float("", 0.0f);
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagFloat = new TAG_Float(getName());
		
		Assert.assertEquals(getName(), tagFloat.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagFloat = new TAG_Float(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagFloat.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(getName(), tagFloat.getName());
		Assert.assertEquals(s_value, tagFloat.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagFloat = new TAG_Float(getName(), s_value);
		
		tagFloat.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value.floatValue(), inStream.readFloat(), 0.001);
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagFloat.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagFloat.getName());
		Assert.assertEquals(s_value, tagFloat.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagFloat.setValue(s_value);
		
		tagFloat.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value.floatValue(), inStream.readFloat(), 0.001);
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagFloat.setValue(s_value);
		
		Assert.assertEquals(s_value, tagFloat.getValue());
	}
	
	public void writeValue() throws IOException {
		outStream.writeFloat(s_value);
	}
}
