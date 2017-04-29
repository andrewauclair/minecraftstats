package stats.spec.nbt.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.Vector3;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Double;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_List;

public class Vector3Specification {
	private Vector3 position;
	private TAG_List list;
	private double x;
	private double y;
	private double z;
	
	@Before
	public void setup() {
		position = new Vector3();
		list = new TAG_List("");
		x = 1.5;
		y = 9.33;
		z = 5.003;
	}
	
	@Test
	public void ShouldCreateObjectWithValues() {
		position = new Vector3(x, y, z);
		
		assertEquals(x, position.getX(), 0.001);
		assertEquals(y, position.getY(), 0.001);
		assertEquals(z, position.getZ(), 0.001);
	}
	
	@Test
	public void ShouldReadFromList() {
		ArrayList<TAG> tags = new ArrayList<>();
		tags.add(new TAG_Double("", x));
		tags.add(new TAG_Double("", y));
		tags.add(new TAG_Double("", z));
		list.setValue(tags);
		
		position.readFromList(list);
		
		assertEquals(x, position.getX(), 0.001);
		assertEquals(y, position.getY(), 0.001);
		assertEquals(z, position.getZ(), 0.001);
	}
	
	@Test
	public void ShouldWriteToList() {
		position.setX(x);
		position.setY(y);
		position.setZ(z);
		
		position.writeToList(list);
		
		ArrayList<TAG> tags = list.getValue();
		
		assertEquals(x, ((TAG_Double)tags.get(0)).getValue(), 0.001);
		assertEquals(y, ((TAG_Double)tags.get(1)).getValue(), 0.001);
		assertEquals(z, ((TAG_Double)tags.get(2)).getValue(), 0.001);
	}
	
	@Test
	public void ShouldIgnoreInvalidInput() {
		ArrayList<TAG> tags = new ArrayList<>();
		tags.add(new TAG_Float("", (float)x));
		tags.add(new TAG_Float("", (float)y));
		tags.add(new TAG_Float("", (float)z));
		list.setValue(tags);
		
		position.readFromList(list);
		
		tags.clear();
		tags.add(new TAG_Double("", x));
		tags.add(new TAG_Double("", y));
		list.setValue(tags);
		
		position.readFromList(list);
		
		tags.remove(tags.size() - 1);
		list.setValue(tags);
		
		position.readFromList(list);
		
		tags.clear();
		list.setValue(tags);
		
		position.readFromList(list);
	}
}
