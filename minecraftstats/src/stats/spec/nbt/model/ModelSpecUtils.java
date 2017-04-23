package stats.spec.nbt.model;

import static org.junit.Assert.*;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_String;

public final class ModelSpecUtils {
	public static void assertTagString(String expected, String tagName, TAG_Compound compound) {
		new ModelSpecUtils();
		assertEquals(expected, ((TAG_String)compound.getTAG(tagName)).getValue());
	}
	
	public static void assertTagByte(byte expected, String tagName, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Byte)compound.getTAG(tagName)).getValue());
	}
	
	public static void assertTagInt(int expected, String tagName, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Int)compound.getTAG(tagName)).getValue());
	}
	
	public static void assertTagLong(long expected, String tagName, TAG_Compound compound) {
		assertEquals(expected, ((TAG_Long)compound.getTAG(tagName)).getValue());
	}
}
