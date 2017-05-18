package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Double;

public class TAG_DoubleSpecification extends TAGSpecCommon {
	private static final double s_value = 15.0;
	
	private TAG_Double tagDouble;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		tagDouble = new TAG_Double("", 0.0);
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagDouble = new TAG_Double(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagDouble.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(getName(), tagDouble.getName());
		assertEquals(s_value, tagDouble.getValue(), 0.001);
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagDouble = new TAG_Double(getName(), s_value);
		
		tagDouble.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(s_value, inStream.readDouble(), 0.001);
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagDouble.readFromStream(inStream, true);
		
		assertEquals("", tagDouble.getName());
		assertEquals(s_value, tagDouble.getValue(), 0.001);
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagDouble.setValue(s_value);
		
		tagDouble.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertEquals(s_value, inStream.readDouble(), 0.001);
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagDouble.setValue(s_value);
		
		assertEquals(s_value, tagDouble.getValue(), 0.001);
	}
	
	public void writeValue() throws IOException {
		outStream.writeDouble(s_value);
	}
}
