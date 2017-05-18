package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Long;

public class TAG_LongSpecification extends TAGSpecCommon {
	private static final long s_value = 15L;
	
	private TAG_Long tagLong;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		tagLong = new TAG_Long("", 0L);
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagLong = new TAG_Long(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		outStream.writeLong(s_value);
		
		createInputStreamFromOutputStream();
		
		tagLong.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		assertEquals(getName(), tagLong.getName());
		assertEquals(s_value, tagLong.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagLong = new TAG_Long(getName(), s_value);
		
		tagLong.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(s_value, inStream.readLong());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		outStream.writeLong(s_value);
		
		createInputStreamFromOutputStream();
		
		tagLong.readFromStream(inStream, true);
		
		assertEquals("", tagLong.getName());
		assertEquals(s_value, tagLong.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagLong.setValue(s_value);
		
		tagLong.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertEquals(s_value, inStream.readLong());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagLong.setValue(s_value);
		
		assertEquals(s_value, tagLong.getValue());
	}
}
