package stats.spec.nbt.model;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Before;

public class SpecTagHelper {

	private ByteArrayInputStream byteInStream = null;
	private ByteArrayOutputStream byteOutStream = null;
	protected DataInputStream inStream = null;
	protected DataOutputStream outStream = null;
	
	@Before
	public void setup() throws IOException {
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
		
		byte[] values = new byte[0];
		
		byteInStream = new ByteArrayInputStream(values);
		inStream = new DataInputStream(byteInStream);
	}
	
	public void writeString(String value) throws IOException {
		outStream.writeShort(value.length());
		outStream.write(value.getBytes());
	}
	
	public void writeByte(Byte value) throws IOException {
		outStream.writeByte(value);
	}
	
	public void writeShort(Short value) throws IOException {
		outStream.writeShort(value);
	}
	
	public void assertReadString(String expected) throws IOException {
		assertEquals(expected.length(), inStream.readShort());
		String actual = new String(readBytesFromStream(expected.length()), "UTF-8");
		assertEquals(expected, actual);
	}
	
	public byte[] readBytesFromStream(int count) throws IOException {
		byte[] bytes = new byte[count];
		inStream.read(bytes);
		return bytes;
	}
}
