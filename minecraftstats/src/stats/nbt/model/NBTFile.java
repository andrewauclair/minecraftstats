package stats.nbt.model;

import java.io.File;

import stats.nbt.model.tags.TAG;

// contains the tree of nbt tags that make up a file
public class NBTFile {

	private File m_file;
	private TAG m_root;
	private boolean m_gZipped;
	
	public NBTFile(final File file, TAG root, boolean gZipped) {
		m_file = file;
		m_root = root;
		m_gZipped = gZipped;
	}
	
	public File getFile() {
		return m_file;
	}
	
	public void setFile(File file) {
		m_file = file;
	}
	
	public TAG getRoot() {
		return m_root;
	}
	
	public boolean isGZipped() {
		return m_gZipped;
	}
}
