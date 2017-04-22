package stats.spec.nbt.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ItemModel;
import stats.nbt.model.tags.TAG_Byte;
import stats.nbt.model.tags.TAG_Compound;
import stats.nbt.model.tags.TAG_Short;
import stats.nbt.model.tags.TAG_String;

public class ItemModelSpecification {

	private ItemModel itemData;
	private byte count;
	private short damage;
	private String itemID;
	
	@Before
	public void setup() throws IOException {
		itemData = new ItemModel();
		count = 5;
		damage = 1;
		itemID = "minecraft:wheat_seeds";
	}
	
	@Test
	public void ShouldReadFromCompound() throws IOException {
		TAG_Compound compound = new TAG_Compound("Item");
		compound.addTAG(new TAG_Byte("Count", count));
		compound.addTAG(new TAG_Short("Damage", damage));
		compound.addTAG(new TAG_String("id", itemID));
		
		itemData.readFromCompound(compound);
		
		assertEquals(count, itemData.getCount());
		assertEquals(damage, itemData.getDamage());
		assertEquals(itemID, itemData.getItemID());
	}
	
	@Test
	public void ShouldWriteToStream() throws IOException {
		
		TAG_Compound compound = new TAG_Compound("Item");
		
		itemData.setCount(count);
		itemData.setDamage(damage);
		itemData.setItemID(itemID);
		
		itemData.writeToCompound(compound);
		
		assertTrue(compound.hasTAG("Count"));
		assertTrue(compound.hasTAG("Damage"));
		assertTrue(compound.hasTAG("id"));
		assertEquals(count, ((TAG_Byte)compound.getTAG("Count")).getValue());
		assertEquals(damage, ((TAG_Short)compound.getTAG("Damage")).getValue());
		assertEquals(itemID, ((TAG_String)compound.getTAG("id")).getValue());
	}
}
