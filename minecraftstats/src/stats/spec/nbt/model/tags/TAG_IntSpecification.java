package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Int;

public class TAG_IntSpecification extends TAGCommonSpecification {

	private static final Integer s_value = 15;
	
	private TAG_Int tagInt = new TAG_Int("", 0);
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagInt = new TAG_Int(getName());
		
		Assert.assertEquals(getName(), tagInt.getName());
	}
	
	@Test
	public void ShouldCreateObjectWithNameAndValue() {
		tagInt = new TAG_Int(getName(), s_value);
		
		assertNameAndValueAreSet();
	}
	
	@Test
	public void ShouldReadDataFromInputStream() throws IOException {
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagInt.readFromStream(inStream, true);
		
		assertNameAndValueAreSet();
	}
	
	private void assertNameAndValueAreSet() {
		Assert.assertEquals(getName(), tagInt.getName());
		Assert.assertEquals(s_value, tagInt.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagInt = new TAG_Int(getName(), s_value);
		
		tagInt.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		Assert.assertEquals(s_value.intValue(), inStream.readInt());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagInt.readFromStream(inStream, true);
		
		Assert.assertEquals("", tagInt.getName());
		Assert.assertEquals(s_value, tagInt.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagInt.setValue(s_value);
		
		tagInt.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		Assert.assertEquals(0, inStream.readShort());
		Assert.assertEquals(s_value.intValue(), inStream.readInt());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagInt.setValue(s_value);
		
		Assert.assertEquals(s_value, tagInt.getValue());
	}
	
	public void writeValue() throws IOException {
		outStream.writeInt(s_value);
	}
}
