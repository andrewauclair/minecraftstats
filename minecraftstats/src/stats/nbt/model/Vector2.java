package stats.nbt.model;

import java.util.ArrayList;

import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Float;

public class Vector2 {

	private float x;
	private float y;
	
	public void readFromList(TAG_List list) {
		if (list.getType() == TAG_Type.TAG_Float) {
			ArrayList<TAG> tags = list.getValue();
			
			x = ((TAG_Float)tags.get(0)).getValue();
			
			if (tags.size() > 1) {
				y = ((TAG_Float)tags.get(1)).getValue();
			}
		}
	}
	
	public void writeToList(TAG_List list) {
		ArrayList<TAG> tags = new ArrayList<>();
		tags.add(new TAG_Float("", x));
		tags.add(new TAG_Float("", y));
		list.setValue(tags);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
