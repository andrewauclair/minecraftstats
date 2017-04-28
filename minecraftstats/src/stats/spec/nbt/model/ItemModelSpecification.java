package stats.spec.nbt.model;

import static org.junit.Assert.*;
import static stats.nbt.model.ItemModel.*;
import static stats.spec.nbt.model.ModelSpecUtils.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ItemModel;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;

public class ItemModelSpecification {
	private TAG_Compound compound;
	private ItemModel itemData;
	private byte count;
	private short damage;
	private String id;
	
	@Before
	public void setup() throws IOException {
		itemData = new ItemModel();
		compound = new TAG_Compound("");
		count = 5;
		damage = 1;
		id = "minecraft:wheat_seeds";
	}
	
	@Test
	public void ShouldSetTagNames() {
		assertEquals("Count", countTagName);
		assertEquals("Damage", damageTagName);
		assertEquals("id", idTagName);
	}
	
	@Test
	public void ShouldReadFromCompound() throws IOException {
		compound.addTAG(new TAG_Byte(countTagName, count));
		compound.addTAG(new TAG_Short(damageTagName, damage));
		compound.addTAG(new TAG_String(idTagName, id));
		
		itemData.readFromCompound(compound);
		
		assertEquals(count, itemData.getCount());
		assertEquals(damage, itemData.getDamage());
		assertEquals(id, itemData.getItemID());
	}
	
	@Test
	public void ShouldWriteToStream() throws IOException {
		itemData.setCount(count);
		itemData.setDamage(damage);
		itemData.setItemID(id);
		
		itemData.writeToCompound(compound);
		
		assertTagByte(count, countTagName, compound);
		assertTagShort(damage, damageTagName, compound);
		assertTagString(id, idTagName, compound);
	}
	
	@Test
	public void ShouldNotThrowExceptionOnEmptyCompound() {
		itemData.readFromCompound(compound);
	}
}
