package stats.nbt.model;

import static stats.nbt.utils.ModelUtils.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_String;

public class VersionModel {
	public static final String idTagName = "Id";
	public static final String nameTagName = "Name";
	public static final String snapshotTagName = "Snapshot";
	
	private int id;
	private String name;
	private byte snapshot;
	
	public void readFromCompound(TAG_Compound compound) {
		id = getIntValue(compound, idTagName);
		name = getStringValue(compound, nameTagName);
		snapshot = getByteValue(compound, snapshotTagName);
	}
	
	public void writeToCompound(TAG_Compound compound) {
		compound.addTAG(new TAG_Int(idTagName, id));
		compound.addTAG(new TAG_String(nameTagName, name));
		compound.addTAG(new TAG_Byte(snapshotTagName, snapshot));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(byte snapshot) {
		this.snapshot = snapshot;
	}
}
