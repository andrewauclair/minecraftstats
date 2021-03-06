package stats.spec.nbt.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import stats.nbt.model.Vector2;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Double;
import stats.nbt.model.tags.TAG_Float;
import stats.nbt.model.tags.TAG_List;

public class Vector2Specification {
	private Vector2 rotation;
	private TAG_List list;
	private float x;
	private float y;
	
	@Before
	public void setup() {
		rotation = new Vector2();
		list = new TAG_List("");
		x = 1.55f;
		y = 1.8f;
	}
	
	@Test
	public void ShouldCreateObjectWithValues() {
		rotation = new Vector2(x, y);
		list = new TAG_List("");
		assertEquals(x, rotation.getX(), 0.001);
		assertEquals(y, rotation.getY(), 0.001);
	}
	
	@Test
	public void ShouldReadFromList() {
		ArrayList<TAG> tags = new ArrayList<>();
		tags.add(new TAG_Float("", x));
		tags.add(new TAG_Float("", y));
		list.setValue(tags);
		
		rotation.readFromList(list);
		
		assertEquals(x, rotation.getX(), 0.001);
		assertEquals(y, rotation.getY(), 0.001);
	}
	
	@Test
	public void ShouldWriteToList() {
		rotation.setX(x);
		rotation.setY(y);
		
		rotation.writeToList(list);
		
		ArrayList<TAG> tags = list.getValue();
		
		assertEquals(x, ((TAG_Float)tags.get(0)).getValue(), 0.001);
		assertEquals(y, ((TAG_Float)tags.get(1)).getValue(), 0.001);
	}
	
	@Test
	public void ShouldIgnoreInvalidInput() {
		ArrayList<TAG> tags = new ArrayList<>();
		tags.add(new TAG_Double("", (double)x));
		tags.add(new TAG_Double("", (double)y));
		list.setValue(tags);
		
		rotation.readFromList(list);
		
		tags.clear();
		tags.add(new TAG_Float("", x));
		list.setValue(tags);
		
		rotation.readFromList(list);
		
		tags.clear();
		list.setValue(tags);
		
		rotation.readFromList(list);
	}
}
