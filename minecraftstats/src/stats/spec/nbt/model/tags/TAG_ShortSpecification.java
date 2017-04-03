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
import stats.nbt.model.tags.TAG_Short;

public class TAG_ShortSpecification extends TAGCommonSpecification {

	private static final Short s_value = 15;
	
	private TAG_Short tagShort = new TAG_Short("", (short)0);
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagShort = new TAG_Short(name);
		
		Assert.assertEquals(name, tagShort.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagShort = new TAG_Short(name, s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		tagShort.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(name, tagShort.getName());
		Assert.assertEquals(s_value, tagShort.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagShort = new TAG_Short(name, s_value);
		
		tagShort.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value.shortValue(), inStream.readShort());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		
		tagShort.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagShort.getName());
		Assert.assertEquals(s_value, tagShort.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearNameInInputStream();
		tagShort.setValue(s_value);
		
		tagShort.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value.shortValue(), inStream.readShort());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagShort.setValue(s_value);
		
		Assert.assertEquals(s_value, tagShort.getValue());
	}

	@Override
	public void writeValue() throws IOException {
		outStream.writeShort(s_value);
	}
}
