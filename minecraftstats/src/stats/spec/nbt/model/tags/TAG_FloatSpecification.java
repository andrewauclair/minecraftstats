package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Float;

public class TAG_FloatSpecification extends TAGSpecCommon {

	private static final float s_value = 15.0f;

	private TAG_Float tagFloat;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		tagFloat = new TAG_Float("", 0.0f);
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
		assertEquals(getName(), tagFloat.getName());
		assertEquals(s_value, tagFloat.getValue(), 0.001);
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagFloat = new TAG_Float(getName(), s_value);
		
		tagFloat.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(s_value, inStream.readFloat(), 0.001);
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagFloat.readFromStream(inStream, true);
		
		assertEquals("", tagFloat.getName());
		assertEquals(s_value, tagFloat.getValue(), 0.001);
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagFloat.setValue(s_value);
		
		tagFloat.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertEquals(s_value, inStream.readFloat(), 0.001);
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagFloat.setValue(s_value);
		
		assertEquals(s_value, tagFloat.getValue(), 0.001);
	}
	
	public void writeValue() throws IOException {
		outStream.writeFloat(s_value);
	}
}
