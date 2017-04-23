package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.VersionModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.VersionModel;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_String;

public class VersionModelSpecification {

	private VersionModel version;
	
	private int id;
	private String name;
	private byte snapshot;
	
	@Before
	public void setup() {
		version = new VersionModel();
		id = 512;
		name = "1.11.2";
		snapshot = 1;
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("Id", idTagName);
		assertEquals("Name", nameTagName);
		assertEquals("Snapshot", snapshotTagName);
	}
	
	@Test
	public void ShouldReadFromCompound() {
		TAG_Compound compound = new TAG_Compound("");
		compound.addTAG(new TAG_Int(idTagName, id));
		compound.addTAG(new TAG_String(nameTagName, name));
		compound.addTAG(new TAG_Byte(snapshotTagName, snapshot));
		
		version.readFromCompound(compound);
		
		assertEquals(id, version.getId());
		assertEquals(name, version.getName());
		assertEquals(snapshot, version.getSnapshot());
	}
	
	@Test
	public void ShouldWriteToCompound() {
		TAG_Compound compound = new TAG_Compound("");
		
		version.setId(id);
		version.setName(name);
		version.setSnapshot(snapshot);
		
		version.writeToCompound(compound);
		
		assertTagInt(id, idTagName, compound);
		assertTagString(name, nameTagName, compound);
		assertTagByte(snapshot, snapshotTagName, compound);
	}
}
