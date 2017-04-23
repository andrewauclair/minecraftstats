package stats.spec.nbt.model.tags;

import java.io.IOException;
import org.junit.Before;

import stats.spec.nbt.model.SpecTagHelper;

public class TAGSpecCommon extends SpecTagHelper {

	private static final String s_name = "Test";
	
	private String name = "";
	
	@Before
	public void setup() throws IOException {
		super.setup();
		name = s_name;
	}
	
	public void writeName() throws IOException {
		writeString(name);
	}
	
	public void assertNameRead() throws IOException {
		assertReadString(name);
	}

	public String getName() {
		return name;
	}

	protected void clearName() {
		name = "";
	}
}
