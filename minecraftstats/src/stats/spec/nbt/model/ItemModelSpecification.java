package stats.spec.nbt.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.ItemModel;

public class ItemModelSpecification extends SpecTagHelper {

	private ItemModel itemData;
	private byte count;
	private short damage;
	private String itemID;
	
	@Before
	public void setup() throws IOException {
		super.setup();
		
		itemData = new ItemModel();
		count = 5;
		damage = 1;
		itemID = "minecraft:wheat_seeds";
	}
	
	@Test
	public void ShouldReadFromStream() throws IOException {
		writeString("Count");
		writeByte(count);
		writeString("Damage");
		writeShort(damage);
		writeString("id");
		writeString(itemID);
		
		createInputStreamFromOutputStream();
		
		itemData.readFromStream(inStream);
		
		assertEquals(count, itemData.getCount());
		assertEquals(damage, itemData.getDamage());
		assertEquals(itemID, itemData.getItemID());
	}
	
	@Test
	public void ShouldWriteToStream() throws IOException {
		
		itemData.setCount(count);
		itemData.setDamage(damage);
		itemData.setItemID(itemID);
		
		itemData.writeToStream(outStream);
		
		createInputStreamFromOutputStream();
		
		assertReadString("Count");
		assertEquals(count, inStream.readByte());
		assertReadString("Damage");
		assertEquals(damage, inStream.readShort());
		assertReadString("id");
		assertReadString(itemID);
	}
}
