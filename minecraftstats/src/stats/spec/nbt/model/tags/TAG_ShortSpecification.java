package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Short;

public class TAG_ShortSpecification extends TAGSpecCommon {

	private static final short s_value = 15;
	
	private TAG_Short tagShort = new TAG_Short("", (short)0);
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagShort = new TAG_Short(getName());
		
		assertEquals(getName(), tagShort.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagShort = new TAG_Short(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagShort.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(getName(), tagShort.getName());
		assertEquals(s_value, tagShort.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagShort = new TAG_Short(getName(), s_value);
		
		tagShort.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(s_value, inStream.readShort());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagShort.readFromStream(inStream, true);
		
		assertEquals("", tagShort.getName());
		assertEquals(s_value, tagShort.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagShort.setValue(s_value);
		
		tagShort.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertEquals(s_value, inStream.readShort());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagShort.setValue(s_value);
		
		assertEquals(s_value, tagShort.getValue());
	}

	public void writeValue() throws IOException {
		writeShort(s_value);
	}
}
