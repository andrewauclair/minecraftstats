package stats.spec.nbt.model.tags;

import static org.junit.Assert.*;

import org.junit.Test;

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

public class TAGSpecification {

	@Test
	public void ShouldReturnNullForInvalidEnumValue() {
		TAG_Type.valueOf("TAG_End");
		assertEquals(null, TAG_Type.fromInt(-1));
	}
	
	@Test
	public void ShouldReturn0ForEndEnumValue() {
		assertEquals(0, TAG_Type.TAG_End.getValue());
	}
	
	@Test
	public void ShouldReturn1ForByteEnumValue() {
		assertEquals(1, TAG_Type.TAG_Byte.getValue());
	}
	
	@Test
	public void ShouldReturn2ForShortEnumValue() {
		assertEquals(2, TAG_Type.TAG_Short.getValue());
	}
	
	@Test
	public void ShouldReturn3ForIntEnumValue() {
		assertEquals(3, TAG_Type.TAG_Int.getValue());
	}
	
	@Test
	public void ShouldReturn4ForLongEnumValue() {
		assertEquals(4, TAG_Type.TAG_Long.getValue());
	}
	
	@Test
	public void ShouldReturn5ForFloatEnumValue() {
		assertEquals(5, TAG_Type.TAG_Float.getValue());
	}
	
	@Test
	public void ShouldReturn6ForDoubleEnumValue() {
		assertEquals(6, TAG_Type.TAG_Double.getValue());
	}
	
	@Test
	public void ShouldReturn7ForByteArrayEnumValue() {
		assertEquals(7, TAG_Type.TAG_Byte_Array.getValue());
	}
	
	@Test
	public void ShouldReturn8ForStringEnumValue() {
		assertEquals(8, TAG_Type.TAG_String.getValue());
	}
	
	@Test
	public void ShouldReturn9ForListEnumValue() {
		assertEquals(9, TAG_Type.TAG_List.getValue());
	}
	
	@Test
	public void ShouldReturn10ForCompoundEnumValue() {
		assertEquals(10, TAG_Type.TAG_Compound.getValue());
	}
	
	@Test
	public void ShouldReturn11ForIntArrayEnumValue() {
		assertEquals(11, TAG_Type.TAG_Int_Array.getValue());
	}
	
	@Test
	public void ShouldReturnTAGEndWhenCallingFromTAGWithTAGEnd() {
		assertEquals(TAG_Type.TAG_End, TAG_Type.fromTAG(new TAG_End()));
	}
	
	@Test
	public void ShouldReturnTAGByteWhenCallingFromTAGWithTAGByte() {
		assertEquals(TAG_Type.TAG_Byte, TAG_Type.fromTAG(new TAG_Byte("", (byte)0)));
	}
	
	@Test
	public void ShouldReturnTAGShortWhenCallingFromTAGWithTAGShort() {
		assertEquals(TAG_Type.TAG_Short, TAG_Type.fromTAG(new TAG_Short("", (short)0)));
	}
	
	@Test
	public void ShouldReturnTAGIntWhenCallingFromTAGWithTAGInt() {
		assertEquals(TAG_Type.TAG_Int, TAG_Type.fromTAG(new TAG_Int("", 0)));
	}
	
	@Test
	public void ShouldReturnTAGLongWhenCallingFromTAGWithTAGLong() {
		assertEquals(TAG_Type.TAG_Long, TAG_Type.fromTAG(new TAG_Long("", 0L)));
	}
	
	@Test
	public void ShouldReturnTAGFloatWhenCallingFromTAGWithTAGFloat() {
		assertEquals(TAG_Type.TAG_Float, TAG_Type.fromTAG(new TAG_Float("", 0.0f)));
	}
	
	@Test
	public void ShouldReturnTAGDoubleWhenCallingFromTAGWithTAGDouble() {
		assertEquals(TAG_Type.TAG_Double, TAG_Type.fromTAG(new TAG_Double("", 0.0)));
	}
	
	@Test
	public void ShouldReturnTAGByteArrayWhenCallingFromTAGWithTAGByteArray() {
		assertEquals(TAG_Type.TAG_Byte_Array, TAG_Type.fromTAG(new TAG_Byte_Array("", new byte[0])));
	}
	
	@Test
	public void ShouldReturnTAGStringWhenCallingFromTAGWithTAGString() {
		assertEquals(TAG_Type.TAG_String, TAG_Type.fromTAG(new TAG_String("", "")));
	}
	
	@Test
	public void ShouldReturnTAGListWhenCallingFromTAGWithTAGList() {
		assertEquals(TAG_Type.TAG_List, TAG_Type.fromTAG(new TAG_List("")));
	}
	
	@Test
	public void ShouldReturnTAGCompoundWhenCallingFromTAGWithTAGCompound() {
		assertEquals(TAG_Type.TAG_Compound, TAG_Type.fromTAG(new TAG_Compound("")));
	}
	
	@Test
	public void ShouldReturnTAGIntArrayWhenCallingFromTAGWithTAGIntArray() {
		assertEquals(TAG_Type.TAG_Int_Array, TAG_Type.fromTAG(new TAG_Int_Array("")));
	}
	
	@Test
	public void ShouldReturnNullWhenCallingFromTAGWithNull() {
		assertEquals(null, TAG_Type.fromTAG(null));
	}
}
