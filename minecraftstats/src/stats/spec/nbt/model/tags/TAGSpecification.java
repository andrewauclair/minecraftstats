package stats.spec.nbt.model.tags;

import org.junit.Assert;
import org.junit.Test;

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

public class TAGSpecification {

	@Test
	public void ShouldReturn0ForEndEnumValue() {
		Assert.assertEquals(0, TAG_Type.TAG_End.getValue());
	}
	
	@Test
	public void ShouldReturn1ForByteEnumValue() {
		Assert.assertEquals(1, TAG_Type.TAG_Byte.getValue());
	}
	
	@Test
	public void ShouldReturn2ForShortEnumValue() {
		Assert.assertEquals(2, TAG_Type.TAG_Short.getValue());
	}
	
	@Test
	public void ShouldReturn3ForIntEnumValue() {
		Assert.assertEquals(3, TAG_Type.TAG_Int.getValue());
	}
	
	@Test
	public void ShouldReturn4ForLongEnumValue() {
		Assert.assertEquals(4, TAG_Type.TAG_Long.getValue());
	}
	
	@Test
	public void ShouldReturn5ForFloatEnumValue() {
		Assert.assertEquals(5, TAG_Type.TAG_Float.getValue());
	}
	
	@Test
	public void ShouldReturn6ForDoubleEnumValue() {
		Assert.assertEquals(6, TAG_Type.TAG_Double.getValue());
	}
	
	@Test
	public void ShouldReturn7ForByteArrayEnumValue() {
		Assert.assertEquals(7, TAG_Type.TAG_Byte_Array.getValue());
	}
	
	@Test
	public void ShouldReturn8ForStringEnumValue() {
		Assert.assertEquals(8, TAG_Type.TAG_String.getValue());
	}
	
	@Test
	public void ShouldReturn9ForListEnumValue() {
		Assert.assertEquals(9, TAG_Type.TAG_List.getValue());
	}
	
	@Test
	public void ShouldReturn10ForCompoundEnumValue() {
		Assert.assertEquals(10, TAG_Type.TAG_Compound.getValue());
	}
	
	@Test
	public void ShouldReturn11ForIntArrayEnumValue() {
		Assert.assertEquals(11, TAG_Type.TAG_Int_Array.getValue());
	}
	
	@Test
	public void ShouldReturnTAGEndWhenCallingFromTAGWithTAGEnd() {
		Assert.assertEquals(TAG_Type.TAG_End, TAG_Type.fromTAG(new TAG_End()));
	}
	
	@Test
	public void ShouldReturnTAGByteWhenCallingFromTAGWithTAGByte() {
		Assert.assertEquals(TAG_Type.TAG_Byte, TAG_Type.fromTAG(new TAG_Byte("", null)));
	}
	
	@Test
	public void ShouldReturnTAGShortWhenCallingFromTAGWithTAGShort() {
		Assert.assertEquals(TAG_Type.TAG_Short, TAG_Type.fromTAG(new TAG_Short("", null)));
	}
	
	@Test
	public void ShouldReturnTAGIntWhenCallingFromTAGWithTAGInt() {
		Assert.assertEquals(TAG_Type.TAG_Int, TAG_Type.fromTAG(new TAG_Int("", null)));
	}
	
	@Test
	public void ShouldReturnTAGLongWhenCallingFromTAGWithTAGLong() {
		Assert.assertEquals(TAG_Type.TAG_Long, TAG_Type.fromTAG(new TAG_Long("", null)));
	}
	
	@Test
	public void ShouldReturnTAGFloatWhenCallingFromTAGWithTAGFloat() {
		Assert.assertEquals(TAG_Type.TAG_Float, TAG_Type.fromTAG(new TAG_Float("", null)));
	}
	
	@Test
	public void ShouldReturnTAGDoubleWhenCallingFromTAGWithTAGDouble() {
		Assert.assertEquals(TAG_Type.TAG_Double, TAG_Type.fromTAG(new TAG_Double("", null)));
	}
	
	@Test
	public void ShouldReturnTAGByteArrayWhenCallingFromTAGWithTAGByteArray() {
		Assert.assertEquals(TAG_Type.TAG_Byte_Array, TAG_Type.fromTAG(new TAG_Byte_Array("", null)));
	}
	
	@Test
	public void ShouldReturnTAGStringWhenCallingFromTAGWithTAGString() {
		Assert.assertEquals(TAG_Type.TAG_String, TAG_Type.fromTAG(new TAG_String("", null)));
	}
	
	@Test
	public void ShouldReturnTAGListWhenCallingFromTAGWithTAGList() {
		Assert.assertEquals(TAG_Type.TAG_List, TAG_Type.fromTAG(new TAG_List("", null)));
	}
	
	@Test
	public void ShouldReturnTAGCompoundWhenCallingFromTAGWithTAGCompound() {
		Assert.assertEquals(TAG_Type.TAG_Compound, TAG_Type.fromTAG(new TAG_Compound("", null)));
	}
	
	@Test
	public void ShouldReturnTAGIntArrayWhenCallingFromTAGWithTAGIntArray() {
		Assert.assertEquals(TAG_Type.TAG_Int_Array, TAG_Type.fromTAG(new TAG_Int_Array("", null)));
	}
}
