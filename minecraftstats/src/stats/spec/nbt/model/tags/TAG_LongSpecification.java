package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Assert;
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
	public void ShouldCreateObjectWithName() {
		tagLong = new TAG_Long(getName(), 0L);
		
		Assert.assertEquals(getName(), tagLong.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagLong = new TAG_Long(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagLong.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(getName(), tagLong.getName());
		Assert.assertEquals(s_value, tagLong.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagLong = new TAG_Long(getName(), s_value);
		
		tagLong.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value, inStream.readLong());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagLong.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagLong.getName());
		Assert.assertEquals(s_value, tagLong.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagLong.setValue(s_value);
		
		tagLong.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value, inStream.readLong());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagLong.setValue(s_value);
		
		Assert.assertEquals(s_value, tagLong.getValue());
	}
	
	public void writeValue() throws IOException {
		outStream.writeLong(s_value);
	}
}
