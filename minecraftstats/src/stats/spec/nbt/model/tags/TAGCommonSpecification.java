package stats.spec.nbt.model.tags;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;

public abstract class TAGCommonSpecification {

	private static final String s_name = "Test";
	
	protected String name = "";
	
	protected ByteArrayInputStream byteInStream = null;
	protected ByteArrayOutputStream byteOutStream = null;
	protected DataInputStream inStream = null;
	protected DataOutputStream outStream = null;
	
	@Before
	public void setUp() throws IOException {
		name = s_name;
		createInputStream();
		createOutputStream();
	}
	
	public void createInputStreamFromOutputStream() {
		
		byte[] bytes = byteOutStream.toByteArray();
		
		byteInStream = new ByteArrayInputStream(bytes);
		inStream = new DataInputStream(byteInStream);
	}
	
	private void createOutputStream() {
		
		byteOutStream = new ByteArrayOutputStream();
		outStream = new DataOutputStream(byteOutStream);
	}
	
	private void createInputStream() throws IOException {
		
		byte[] values = writeValues();
		
		byteInStream = new ByteArrayInputStream(values);
		inStream = new DataInputStream(byteInStream);
	}
	
	public void clearNameInInputStream() throws IOException {
		name = "";
		createInputStream();
	}
	
	private byte[] writeValues() throws IOException {
		byteOutStream = new ByteArrayOutputStream();
		outStream = new DataOutputStream(byteOutStream);
		
		outStream.writeShort(name.length());
		outStream.write(name.getBytes());
		
		writeValue();
		
		return byteOutStream.toByteArray();
	}
	
	public abstract void writeValue() throws IOException;
	
	public void assertNameRead() throws IOException {
		Assert.assertEquals(name.length(), inStream.readShort());
		Assert.assertTrue(Arrays.equals(name.getBytes(), readBytesFromStream(name.length())));
	}
	
	public byte[] readBytesFromStream(int count) throws IOException {
		byte[] bytes = new byte[count];
		inStream.read(bytes);
		return bytes;
	}
}
