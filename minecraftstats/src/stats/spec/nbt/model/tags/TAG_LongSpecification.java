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
import stats.nbt.model.tags.TAG_Long;

public class TAG_LongSpecification extends TAGCommonSpecification {

	private static final Long s_value = 15L;
	
	private TAG_Long tagLong = new TAG_Long("", 0L);
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagLong = new TAG_Long(name);
		
		Assert.assertEquals(name, tagLong.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagLong = new TAG_Long(name, s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagLong.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(name, tagLong.getName());
		Assert.assertEquals(s_value, tagLong.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagLong = new TAG_Long(name, s_value);
		
		tagLong.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value.longValue(), inStream.readLong());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		
		tagLong.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagLong.getName());
		Assert.assertEquals(s_value, tagLong.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagLong.setValue(s_value);
		
		tagLong.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value.longValue(), inStream.readLong());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagLong.setValue(s_value);
		
		Assert.assertEquals(s_value, tagLong.getValue());
	}
	
	@Override
	public void writeValue() throws IOException {
		outStream.writeLong(s_value);
	}
}
