package stats.nbt.model.tags.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import stats.nbt.model.tags.TAG_End;
import stats.nbt.model.tags.TAG_List;

public class TAG_EndTest extends TestCase {

	public void testTAGEndRead() throws IOException {

		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(expectedStream.toByteArray());
		DataInputStream nbtIn = new DataInputStream(byteStream);
		
		TAG_End nbtTAG = new TAG_End(null);
		nbtTAG.readFromStream(nbtIn, true);
	}
	
	public void testTAGEndWrite() throws IOException {
		
		TAG_End nbtTAG = new TAG_End(null);
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream nbtOut = new DataOutputStream(byteStream);
		nbtTAG.writeToStream(nbtOut, true);
		
		ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
		DataOutputStream testOut = new DataOutputStream(expectedStream);
		
		assertTrue("TAG_End write is incorrect.", Arrays.equals(expectedStream.toByteArray(), byteStream.toByteArray()));
	}
	
	public void testTAGEndParent() {
		
		TAG_List nbtList = new TAG_List("", null);
		TAG_End nbtTAG = new TAG_End(nbtList);
		
		assertEquals(nbtList, nbtTAG.getParent());
	}
}
