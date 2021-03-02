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
	public void test_empty_name() {
		parser.add("", Parser.STRING);
		assertEquals(parser.parse("--"), 0); //BUG: says error?
	}
	
	@Test
	public void test_optionWithNoShortcut() {
		parser.add("filename", Parser.STRING);
		parser.parse("--filename 1.txt");
		assertEquals(parser.getString("filename"), "1.txt");
	}
	
	@Test
	public void test_optionWithShortcut() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output out.txt");
		assertEquals(parser.getString("output"), "out.txt");
	}
	
	@Test
	public void test_multipleInputArgs() {
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
	public void test_name_sameAsShortcut() {
		parser.add("opt", "opt", Parser.BOOLEAN);
		parser.parse("-opt yes");
		assertEquals(parser.getBoolean("opt"), true);
	}
	
	@Test
	public void test_name_quotations() {
		parser.add("opt", "o", Parser.STRING);
		
		parser.parse("--opt \"words\"");
		assertEquals(parser.getString("opt"), "words");
		
		parser.parse("--opt 'words'");
		assertEquals(parser.getString("opt"), "words");
		
		parser.parse("--opt '\"words'");
		assertEquals(parser.getString("opt"), "\"words");
		
		parser.parse("--opt \"'words\"");
		assertEquals(parser.getString("opt"), "'words");
		
		parser.parse("--opt 'words");
		assertEquals(parser.getString("opt"), "'words"); //BUG: ' should be included in the String value
	}
	
	@Test
	public void test_name_caseSensitivity() {
		parser.add("opt", Parser.STRING);
		parser.add("oPt", Parser.STRING);
		
		parser.parse("--opt val");
		parser.parse("--oPt nice");
		assertEquals(parser.getString("opt"), "val");
		assertEquals(parser.getString("oPt"), "nice");
	}
	
	@Test
	public void test_valueType_defaults() {
		parser.add("test1", Parser.STRING);
		parser.parse("--test1");
		assertEquals(parser.getString("test1"), "");
		
		parser.add("test2", Parser.BOOLEAN);
		parser.parse("--test2");
		assertEquals(parser.getBoolean("test2"), false); //BUG: default value should be false
		
		parser.add("test3", Parser.CHAR);
		parser.parse("--test3");
		assertEquals(parser.getChar("test3"), '\0');
		
		parser.add("test4", Parser.INTEGER);
		parser.parse("--test4");
		assertEquals(parser.getInteger("test4"), 0);
		
	}
	
	@Test
	public void test_shortcut_overwrite() {
		
	}

	@Test
	public void test_shortcut_sameAsName() {
		
	}
	
	@Test
	public void test_shortcut_quotations() {
		
	}
	
	@Test
	public void test_shortcut_caseSensitivity() {
		
	}

	
	@Test
	public void test_int_cases() {
		
	}
	
	@Test
	public void test_string_cases() {
		
	}
	
	@Test
	public void test_boolean_cases() {
		parser.add("option", Parser.BOOLEAN);
		
		//TRUE
		parser.parse("--option 9rfg");
		assertEquals(parser.getBoolean("option"), true);
		
		parser.parse("--option 00");
		assertEquals(parser.getBoolean("option"), true);
		
		parser.parse("--option");
		assertEquals(parser.getBoolean("option"), true);
		
		parser.parse("--option ffalse");
		assertEquals(parser.getBoolean("option"), true);
		
		//FALSE
		parser.parse("--option 0");
		assertEquals(parser.getBoolean("option"), false);
		
		parser.parse("--option fAlsE");
		assertEquals(parser.getBoolean("option"), false);
	}
	
	@Test
	public void test_char_cases() {
		
	}
	
	@Test
	public void test_getType_sameAsShortcut() {
		
	}
	
	@Test
	public void test_getType_sameAsName() {
		
	}
	
	@Test // Overwriting an option
	public void test_parse_returnValues_extinctShortcut() {
		parser.add("filename", "f", Parser.STRING);
		parser.add("filename", Parser.STRING);
		assertNotEquals(parser.parse("-f valGang"), 0); //BUG: f should not exist as a shortcut (should be invalid input)
	}
	
	@Test // Overwriting an option
	public void test_parse_returnValues_extinctName() {
		parser.add("filename", "f", Parser.STRING);
		parser.add("filename", Parser.STRING);
		assertNotEquals(parser.parse("--f val"), 0); //BUG: f should not exist as an option name (should be invalid input)
	}

	
	@Test // Value that starts with " ="
	public void test_value_containsEquals() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt =nice");
		assertEquals(parser.getString("opt"), "=nice"); //BUG: "<name> =<value>" works when only "<name>=<value>" should
	}
}
