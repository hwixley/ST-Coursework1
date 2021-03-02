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
		parser.parse("--output out.txt");
		assertEquals(parser.getString("output"), "out.txt");
	}
	
	@Test
	public void test_input_args() {
		parser.add("filename", Parser.STRING);
		parser.add("output", "o", Parser.STRING);
		parser.parse("--filename 2.txt --output output.txt");
		// Full option names
		assertEquals(parser.getString("filename"), "2.txt");
		assertEquals(parser.getString("output"), "output.txt");
		// Output option shortcut
		assertEquals(parser.getString("o"), "output.txt");
	}
	
	@Test
	public void test_exclude_tags() {
		
	}
	
	@Test
	public void test_boolean_type_true() {
		parser.add("option", Parser.BOOLEAN);
		
		parser.parse("--option 9rfg");
		assertEquals(parser.getString("option"), true);
		
		parser.parse("--option 00");
		assertEquals(parser.getString("option"), true);
		
		parser.parse("--option False");
		assertEquals(parser.getString("option"), true);
		
		parser.parse("--option");
		assertEquals(parser.getString("option"), true);
	}
}
