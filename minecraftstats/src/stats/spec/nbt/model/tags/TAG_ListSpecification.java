package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_List;

// Test list with an arraylist of each tag, that will be 11 test?
// nested lists, nested with compound

// need to do some form of full structure test with all of the tags

public class TAG_ListSpecification extends TestCase {

	private static final String s_name = "Test";
	
	@Test
	public void ShouldCreateObjectWithName() {
		
		//Assert.assertEquals(name, .getName());
	}
	
	public void testTAGListRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeByte(1);
		testOut.writeInt(1);
		testOut.writeByte(0);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_List nbtTAG = new TAG_List("");
		nbtTAG.readFromStream(nbtIn, true);
		
		assertEquals(s_name, nbtTAG.getName());
		assertEquals(1, ((ArrayList<TAG>)nbtTAG.getValue()).size());
	}
	
	public void testTAGListWrite() throws IOException {
		
		TAG_List nbtTAG = new TAG_List(s_name);
		ArrayList<TAG> value = new ArrayList<>();
		value.add(new TAG_Byte("", (byte) 0));
	
		nbtTAG.setValue(value);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		testOut.writeShort(s_name.length());
		testOut.write(s_name.getBytes());
		testOut.writeByte(1);
		testOut.writeInt(1);
		testOut.writeByte(0);
		
		assertTrue("TAG_List write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
}
