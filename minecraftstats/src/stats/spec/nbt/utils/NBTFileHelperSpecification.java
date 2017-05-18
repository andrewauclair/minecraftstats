package stats.spec.nbt.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Double;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.utils.NBTFileHelper;
import stats.spec.nbt.model.SpecTagHelper;

public class NBTFileHelperSpecification extends SpecTagHelper {
	private String name;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		name = "Test";
	}
	
	@Test
	public void ShouldReadTagTypes() throws IOException {
		assertReadsTagType(new TAG_Short(name, (short)0));
		assertReadsTagType(new TAG_Long(name, 0L));
		assertReadsTagType(new TAG_Float(name, 0.0f));
		assertReadsTagType(new TAG_Double(name, 0.0));
		assertReadsTagType(new TAG_Byte_Array(name, new byte[0]));
		assertReadsTagType(new TAG_String(name, ""));
		assertReadsTagType(new TAG_List(name));
		assertReadsTagType(new TAG_Int_Array(name));
	}
	
	private void assertReadsTagType(TAG tag) throws IOException {
		createOutputStream();
		tag.writeToStream(outStream, true);
		createInputStreamFromOutputStream();
		TAG outTag = NBTFileHelper.readTagPayload(inStream, TAG_Type.fromTAG(tag), true);
		assertEquals(name, outTag.getName());
		assertEquals(tag.getClass(), outTag.getClass());
	}
}
