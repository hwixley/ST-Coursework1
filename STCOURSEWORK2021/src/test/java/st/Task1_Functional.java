package st;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class Task1_Functional {

private Parser parser;	

	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	@Test
	public void test_filename_arg() {
		parser.add("filename", Parser.STRING);
		parser.parse("--filename 1.txt");
		assertEquals(parser.getString("filename"), "1.txt");
	}
	
	@Test
	public void test_output_arg() {
		parser.add("output", "o", Parser.STRING);
	}
}
