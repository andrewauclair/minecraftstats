package stats.nbt.utils;

import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Byte_Array;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_Int;
import stats.nbt.model.tags.TAG_Long;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;

public final class ModelUtils {
	public static String getStringValue(TAG_Compound compound, String name) {
		TAG_String tag = (TAG_String)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return "";
	}
	
	public static byte getByteValue(TAG_Compound compound, String name) {
		TAG_Byte tag = (TAG_Byte)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return 0;
	}
	
	public static int getIntValue(TAG_Compound compound, String name) {
		TAG_Int tag = (TAG_Int)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return 0;
	}
	
	public static long getLongValue(TAG_Compound compound, String name) {
		TAG_Long tag = (TAG_Long)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return 0;
	}
	
	public static float getFloatValue(TAG_Compound compound, String name) {
		TAG_Float tag = (TAG_Float)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return 0f;
	}
	
	public static short getShortValue(TAG_Compound compound, String name) {
		TAG_Short tag = (TAG_Short)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return 0;
	}
	
	public static byte[] getByteArrayValue(TAG_Compound compound, String name) {
		TAG_Byte_Array tag = (TAG_Byte_Array)compound.getTAG(name);
		if (tag != null) {
			return tag.getValue();
		}
		return new byte[0];
	}
}
