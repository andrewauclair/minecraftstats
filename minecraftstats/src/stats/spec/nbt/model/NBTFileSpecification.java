package stats.spec.nbt.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.NBTFile;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Byte;

public class NBTFileSpecification {
	private NBTFile file;
	private String path;
	private TAG root;
	private boolean gZipped;
	
	@Before
	public void setup() {
		path = "Testing";
		root = new TAG_Byte("Test", (byte)3);
		gZipped = true;
		file = new NBTFile(path, root, gZipped);
	}
	
	@Test
	public void ShouldSetValuesOnCreate() {
		assertEquals(path, file.getPath());
		assertEquals(root, file.getRoot());
		assertEquals(gZipped, file.isGZipped());
	}
	
	@Test
	public void ShouldSetPath() {
		String newPath = "Different";
		
		file.setPath(newPath);
		
		assertEquals(newPath, file.getPath());
	}
}
