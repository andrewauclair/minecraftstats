package stats.nbt.model;

import stats.nbt.model.tags.TAG;

// contains the tree of nbt tags that make up a file
public class NBTFile {

	private String path;
	private TAG root;
	private boolean gZipped;
	
	public NBTFile(String path, TAG root, boolean gZipped) {
		this.path = path;
		this.root = root;
		this.gZipped = gZipped;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public TAG getRoot() {
		return root;
	}
	
	public boolean isGZipped() {
		return gZipped;
	}
}
