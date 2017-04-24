package stats.nbt.model;

import java.util.ArrayList;

import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_List;
import stats.nbt.model.tags.TAG.TAG_Type;
import stats.nbt.model.tags.TAG_Double;

public class Vector3 {

	private double x;
	private double y;
	private double z;
	
	public Vector3() {
	}
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void readFromList(TAG_List list) {
		if (list.getType() == TAG_Type.TAG_Double) {
			ArrayList<TAG> tags = list.getValue();
			
			x = ((TAG_Double)tags.get(0)).getValue();
			
			if (tags.size() > 1) {
				y = ((TAG_Double)tags.get(1)).getValue();
			}
			
			if (tags.size() > 2) {
				z = ((TAG_Double)tags.get(2)).getValue();
			}
		}
	}
	
	public void writeToList(TAG_List list) {
		ArrayList<TAG> tags = new ArrayList<>();
		tags.add(new TAG_Double("", x));
		tags.add(new TAG_Double("", y));
		tags.add(new TAG_Double("", z));
		list.setValue(tags);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
}
