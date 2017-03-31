package stats.spec.nbt.model.tags;

import org.junit.Assert;
import org.junit.Test;

import stats.nbt.model.tags.TAG.TAG_Type;

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
}
