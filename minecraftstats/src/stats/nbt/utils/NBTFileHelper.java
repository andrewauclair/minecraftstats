package stats.nbt.utils;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import stats.nbt.model.NBTFile;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Double;
import stats.nbt.model.tags.TAG_End;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Int_Array;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;

public final class NBTFileHelper {

	public static TAG readNextTag(DataInput in, TAG parent) throws IOException {
		TAG_Type type = null;
		type = TAG_Type.valueOf(in.readByte() & 0xFF);
		TAG newTag = null;
		
		//System.out.println("Found tag: " + type);
		
		return readTagPayload(in, parent, type, true);
	}
	
	public static TAG readTagPayload(DataInput in, TAG parent, TAG_Type type, boolean readName) throws IOException {
		
		TAG newTag = null;
		
		switch (type) {
		case TAG_End:
			newTag = new TAG_End(parent);
			break;
		case TAG_Byte:
			newTag = new TAG_Byte("", parent);
			break;
		case TAG_Short:
			newTag = new TAG_Short("", parent);
			break;
		case TAG_Int:
			newTag = new TAG_Int("", parent);
			break;
		case TAG_Long:
			newTag = new TAG_Long("", parent);
			break;
		case TAG_Float:
			newTag = new TAG_Float("", parent);
			break;
		case TAG_Double:
			newTag = new TAG_Double("", parent);
			break;
		case TAG_Byte_Array:
			newTag = new TAG_Byte_Array("", parent);
			break;
		case TAG_String:
			newTag = new TAG_String("", parent);
			break;
		case TAG_List:
			newTag = new TAG_List("", parent);
			break;
		case TAG_Compound:
			newTag = new TAG_Compound("", parent);
			break;
		case TAG_Int_Array:
			newTag = new TAG_Int_Array("", parent);
			break;
		}

		newTag.readFromStream(in, readName);
		
		return newTag;
	}
	
	// Reads a file and returns the TAG representing it
	public static NBTFile readNBTFile(final File file) throws IOException {
		
		if (!file.exists()) {
			return null;
		}
		
		// attempt to read as a gzip file
		boolean gzipped = true;
		
		DataInputStream input = null;
		
		try {
			FileInputStream fileStream = new FileInputStream(file);
			GZIPInputStream gzipStream = new GZIPInputStream(fileStream);
			
			input = new DataInputStream(gzipStream);
		}
		catch (IOException e) {
			System.out.println("Not a GZipped file.");
			input = new DataInputStream(new FileInputStream(file));
		}
		
		TAG root = readNextTag(input, null);
		
		System.out.println("Finished reading file.");
		
		return new NBTFile(file, root, gzipped);
		
		// NBTExplorer appears to sort the byte, short, int, long, string, double and float types
		// it puts lists at the beginning and byte arrays at the end
		// if reading as gzip fails, try to read as a normal file
		
		// if this also fails, return null
	}
	
	// Writes an NBT Tag to the given file
	public static void writeNBTFile(NBTFile file) {
		
		// write the TAG to the file
		
		// if requested, compress the file using gzip
	}
	
	public static void main(String[] args) {
		try {
			//readNBTFile(new File("idcounts.dat"));
			//readNBTFile(new File("map_0.dat"));
			//readNBTFile(new File("level.dat"));
			//readNBTFile(new File("r.0.0.mca"));
			readNBTFile(new File("1a0857f7-b10c-457b-b136-9643b4f26ab4.dat"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
