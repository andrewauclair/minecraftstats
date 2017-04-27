package stats.spec.nbt.model;

import static org.junit.Assert.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;
import stats.nbt.utils.ModelUtils;

public final class ModelSpecUtils {
	public static void assertTagString(String expected, String name, TAG_Compound compound) {
		new ModelSpecUtils();
		new ModelUtils();
		assertEquals(expected, ((TAG_String)compound.getTAG(name)).getValue());
	}
	
	public static void assertTagByte(byte expected, String name, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Byte)compound.getTAG(name)).getValue());
	}
	
	public static void assertTagInt(int expected, String name, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Int)compound.getTAG(name)).getValue());
	}
	
	public static void assertTagLong(long expected, String name, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Long)compound.getTAG(name)).getValue());
	}
	
	public static void assertTagFloat(float expected, String name, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Float)compound.getTAG(name)).getValue(), 0.001);
	}
	
	public static void assertTagShort(short expected, String name, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Short)compound.getTAG(name)).getValue());
	}
	
	public static void assertTagByteArray(byte[] expected, String name, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Byte_Array)compound.getTAG(name)).getValue());
	}
}
