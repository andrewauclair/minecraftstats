package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

import stats.nbt.model.tags.TAG_End;

public class TAG_EndSpecification extends TAGCommonSpecification {

	private TAG_End tagEnd = new TAG_End();
	
	@Test
	public void ShouldReadNothingFromStream() throws IOException {
		int size = inStream.available();
		tagEnd.readFromStream(inStream, true);
		tagEnd.readPayloadFromStream(inStream);
		
		assertEquals(size, inStream.available());
	}

	@Test
	public void ShouldWriteNothingToStream() throws IOException {
		int size = outStream.size();
		tagEnd.writeToStream(outStream, true);
		tagEnd.writePayloadToStream(outStream);
		
		assertEquals(size, outStream.size());
	}
}
