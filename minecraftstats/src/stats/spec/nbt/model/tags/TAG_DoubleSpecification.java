package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Double;

public class TAG_DoubleSpecification extends TAGCommonSpecification {

	private static final Double s_value = 15.0;
	
	private TAG_Double tagDouble = new TAG_Double("", 0.0);
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagDouble = new TAG_Double(name, s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagDouble.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(name, tagDouble.getName());
		Assert.assertEquals(s_value, tagDouble.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagDouble = new TAG_Double(name, s_value);
		
		tagDouble.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value.doubleValue(), inStream.readDouble(), 0.001);
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		
		tagDouble.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagDouble.getName());
		Assert.assertEquals(s_value, tagDouble.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagDouble.setValue(s_value);
		
		tagDouble.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value.doubleValue(), inStream.readDouble(), 0.001);
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagDouble.setValue(s_value);
		
		Assert.assertEquals(s_value, tagDouble.getValue());
	}
	
	@Override
	public void writeValue() throws IOException {
		outStream.writeDouble(s_value);
	}
}
