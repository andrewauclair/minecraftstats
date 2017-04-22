package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_End;

public class TAG_CompoundSpecification extends TAGCommonSpecification {

	private TAG_Compound tagCompound;
	TAG_Byte value1 = new TAG_Byte("value1", (byte)0);
	TAG_Byte value2 = new TAG_Byte("value2", (byte)0);
	
	@Before
	public void setUp() throws IOException {
		super.setup();
		tagCompound = new TAG_Compound("");
	}
	
	@Test
	public void ShouldCreateObjectWithName() {
		tagCompound = new TAG_Compound(getName());
		
		assertEquals(getName(), tagCompound.getName());
	}
	
	@Test
	public void ShouldReadValuesFromStream() throws IOException {
		
		outStream.writeShort(getName().length());
		outStream.write(getName().getBytes());
		outStream.writeByte(1);
		outStream.writeShort("value1".length());
		outStream.write("value1".getBytes());
		outStream.writeByte(0);
		outStream.writeByte(1);
		outStream.writeShort("value2".length());
		outStream.write("value2".getBytes());
		outStream.writeByte(0);
		outStream.writeByte(0);
		
		createInputStreamFromOutputStream();
		
		tagCompound.readFromStream(inStream, true);
		
		assertEquals(getName(), tagCompound.getName());
		assertTrue(tagCompound.hasTAG("value1"));
		assertTrue(tagCompound.hasTAG("value2"));
		assertEquals(2, tagCompound.count());
	}
	
	@Test
	public void ShouldWriteValuesToStream() throws IOException {
		tagCompound = new TAG_Compound(getName());
		tagCompound.addTAG(value1);
		tagCompound.addTAG(value2);
		
		tagCompound.writeToStream(outStream, true);
		
		createInputStreamFromOutputStream();
		
		assertNameRead();
		assertEquals(1, inStream.readByte());
		assertReadString("value2");
		assertEquals(0, inStream.readByte());
		assertEquals(1, inStream.readByte());
		assertReadString("value1");
		assertEquals(0, inStream.readByte());
		assertEquals(0, inStream.readByte());
	}
	
	@Test
	public void ShouldReturnValuesForGetTag() {
		tagCompound = new TAG_Compound(getName());
		tagCompound.addTAG(value1);
		tagCompound.addTAG(value2);
		
		assertEquals(null, tagCompound.getTAG("nothing"));
		assertEquals(value1, tagCompound.getTAG("value1"));
	}
	
	@Test
	public void ShouldNotAllowAddOfEndTAG() {
		tagCompound.addTAG(new TAG_End());
		
		assertEquals(0, tagCompound.count());
	}
	
	@Test
	public void ShouldRemoveTAG() {
		tagCompound.addTAG(value1);
		
		tagCompound.removeTAG(value1.getName());
		
		assertFalse(tagCompound.hasTAG(value1.getName()));
	}
}
