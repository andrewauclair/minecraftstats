package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Int;

public class TAG_IntSpecification extends TAGSpecCommon {

	private static final int s_value = 15;
	
	private TAG_Int tagInt;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		tagInt = new TAG_Int("", 0);
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
		assertEquals(getName(), tagInt.getName());
		assertEquals(s_value, tagInt.getValue());
	}
	
	@Test
	public void ShouldWriteDataToOutputStream() throws IOException {
		tagInt = new TAG_Int(getName(), s_value);
		
		tagInt.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(s_value, inStream.readInt());
	}
	
	@Test
	public void ShouldReadDataWithEmptyName() throws IOException {
		clearName();
		writeName();
		writeValue();
		
		createInputStreamFromOutputStream();
		
		tagInt.readFromStream(inStream, true);
		
		assertEquals("", tagInt.getName());
		assertEquals(s_value, tagInt.getValue());
	}

	@Test
	public void ShouldWriteDataWithEmptyName() throws IOException {
		clearName();
		tagInt.setValue(s_value);
		
		tagInt.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertEquals(0, inStream.readShort());
		assertEquals(s_value, inStream.readInt());
	}
	
	@Test
	public void ShouldAllowSetAndGetOfValue() {
		tagInt.setValue(s_value);
		
		assertEquals(s_value, tagInt.getValue());
	}
	
	public void writeValue() throws IOException {
		outStream.writeInt(s_value);
	}
}
