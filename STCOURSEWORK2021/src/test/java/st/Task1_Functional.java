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
	public void test_11() {
		parser.add("output","o", Parser.STRING);
		parser.add("filename", Parser.STRING);
		
		assertEquals(parser.parse("--filename 1.txt -o 2.txt"), 0);
		assertEquals(parser.getString("filename"), "1.txt");
		assertEquals(parser.getString("output"), "2.txt");
	}
	
	@Test
	public void test_13_shortcut() {
		parser.add("output","o", Parser.STRING);
		parser.parse("-o value");
		assertEquals(parser.getString("output"), parser.getString("o"));
	}
	
	@Test
	public void test_13_1_overwriteName() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o niceVal");
		
		parser.add("output", "o", Parser.STRING);
		
		assertEquals(parser.getString("output"), "");
		assertEquals(parser.getString("o"), "");
	}
	
	@Test (expected = RuntimeException.class)
	public void test_13_2_digitAsFirstLetter_name() {
		parser.add("1test", "t", Parser.STRING);
	}
	
	@Test (expected = RuntimeException.class)
	public void test_13_2_digitAsFirstLetter_shortcut() {
		parser.add("test", "1t", Parser.STRING);
	}
	
	@Test (expected = RuntimeException.class)
	public void test_13_2_nameContainsBadChars() {
		String[] badChars = {"-",",",".","<",">","/","?","#","'","@","~",":",";","]","[","}","{","=","+","-",")","(","\\","*","&","^","%","$"};
		
		for (int i = 0; i < badChars.length; i++) {
			String badChar = badChars[i];
			parser.add(badChar+"test", "t", Parser.STRING);
		}
	}
	
	@Test (expected = RuntimeException.class)
	public void test_13_2_shortcutContainsBadChars() {
		String[] badChars = {"-",",",".","<",">","/","?","#","'","@","~",":",";","]","[","}","{","=","+","-",")","(","\\","*","&","^","%","$"};
		
		for (int i = 0; i < badChars.length; i++) {
			String badChar = badChars[i];
			parser.add("test", "t"+badChar, Parser.STRING);
		}
	}
	
	@Test
	public void test_13_3_nameCaseSense() {
		parser.add("tEst","t", Parser.STRING);
		assertNotEquals(parser.parse("--test"), 0);
	}
	
	@Test
	public void test_13_3_shortcutCaseSense() {
		parser.add("tEst","T", Parser.STRING);
		assertNotEquals(parser.parse("-t"), 0);
	}
	
	@Test
	public void test_13_4_sameNameAsShortcut() {
		parser.add("test", "t",Parser.STRING);
		parser.add("t","t1", Parser.STRING);
		
		parser.parse("--t val1 -t val2");
		
		assertEquals(parser.getString("test"), "val2");
		assertEquals(parser.getString("t"), "val1");
	}
	
	@Test
	public void test_13_5_boolean_cases() {
		parser.add("option","o", Parser.BOOLEAN);
		
		//TRUE
		parser.parse("-o 9rfg");
		assertEquals(parser.getBoolean("option"), true);
		
		parser.parse("-o 00");
		assertEquals(parser.getBoolean("option"), true);
		
		parser.parse("-o");
		assertEquals(parser.getBoolean("option"), true);
		
		parser.parse("-o ffalse");
		assertEquals(parser.getBoolean("option"), true);
		
		//FALSE
		parser.parse("-o 0");
		assertEquals(parser.getBoolean("option"), false);
		
		parser.parse("-o fAlsE");
		assertEquals(parser.getBoolean("option"), false);
	}
	
	@Test (expected = RuntimeException.class)
	public void test_invalidType() {
		parser.add("option", Parser.CHARLIST);
	}
	
	public void test_14_1_overwriteName() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output niceVal");
		
		parser.add("output", "o", Parser.STRING);
		
		assertEquals(parser.getString("output"), "");
	}
	
	@Test (expected = RuntimeException.class)
	public void test_14_2_digitAsFirstLetter_name() {
		parser.add("1test", "t", Parser.STRING);
	}
	
	@Test (expected = RuntimeException.class)
	public void test_14_2_digitAsFirstLetter_shortcut() {
		parser.add("test", "1t", Parser.STRING);
	}
	
	@Test (expected = RuntimeException.class)
	public void test_14_2_nameContainsBadChars() {
		String[] badChars = {"-",",",".","<",">","/","?","#","'","@","~",":",";","]","[","}","{","=","+","-",")","(","\\","*","&","^","%","$"};
		
		for (int i = 0; i < badChars.length; i++) {
			String badChar = badChars[i];
			parser.add(badChar+"test", "t", Parser.STRING);
		}
	}
	
	@Test (expected = RuntimeException.class)
	public void test_14_2_shortcutContainsBadChars() {
		String[] badChars = {"-",",",".","<",">","/","?","#","'","@","~",":",";","]","[","}","{","=","+","-",")","(","\\","*","&","^","%","$"};
		
		for (int i = 0; i < badChars.length; i++) {
			String badChar = badChars[i];
			parser.add("test", "t"+badChar, Parser.STRING);
		}
	}
	
	@Test
	public void test_14_3_nameCaseSense() {
		parser.add("tEst","t", Parser.STRING);
		assertNotEquals(parser.parse("--test"), 0);
	}
	
	@Test
	public void test_14_3_shortcutCaseSense() {
		parser.add("tEst","T", Parser.STRING);
		assertNotEquals(parser.parse("-t"), 0);
	}
	
	@Test
	public void test_14_4_sameNameAsShortcut() {
		parser.add("test", "t",Parser.STRING);
		parser.add("t","t1", Parser.STRING);
		
		parser.parse("--t val1 -t val2");
		
		assertEquals(parser.getString("test"), "val2");
		assertEquals(parser.getString("t"), "val1");
	}
	
	@Test
	public void test_14_5_boolean_cases() {
		parser.add("option","o", Parser.BOOLEAN);
		
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
	public void test_15_1() {
		parser.add("name", "n", Parser.STRING);
		assertNotEquals("15_1: should be invalid input",parser.parse("--n value"),0);
	}
	
	@Test
	public void test_15_2() {
		parser.add("name", Parser.STRING);
		assertNotEquals("15_2: should be invalid input",parser.parse("-name value"),0);
	}
	
	@Test
	public void test_15_3_1() {
		parser.add("opt", Parser.BOOLEAN);
		parser.parse("--opt=value");
		assertEquals(parser.getBoolean("opt"), true);
	}
	
	@Test
	public void test_15_3_2() {
		parser.add("opt", Parser.BOOLEAN);
		parser.parse("--opt value");
		assertEquals(parser.getBoolean("opt"), true);
	}
	
	@Test
	public void test_15_4_0() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt='value=\"abc\" '");
		assertEquals(parser.getString("opt"), "value=\"abc\"");
	}
	
	@Test
	public void test_15_4_1() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt=\"value\"");
		assertEquals(parser.getString("opt"), "value");
	}
	
	@Test
	public void test_15_4_2() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt='value'");
		assertEquals(parser.getString("opt"), "value");
	}
	
	@Test
	public void test_15_4_3() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt=\"'value'\"");
		assertEquals(parser.getString("opt"), "'value'");
	}
	
	@Test
	public void test_15_4_4() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt='\"value\"'");
		assertEquals(parser.getString("opt"), "\"value\"");
	}
	
	@Test
	public void test_15_5() {
		parser.add("opt", Parser.STRING);
		parser.parse("--opt 0 --opt 1 --opt 24 --opt 69 --opt 2");
		assertEquals(parser.getString("opt"), "2");
	}
	
	@Test
	public void test_15_6_1() {
		parser.add("opt", "o", Parser.STRING);
		
		parser.parse("--opt");
		assertEquals(parser.getString("opt"), "");
	}
	
	@Test
	public void test_15_6_2() {
		parser.add("opt", "o", Parser.BOOLEAN);
		
		parser.parse("--opt");
		assertEquals(parser.getBoolean("opt"), false);
	}
	
	@Test
	public void test_15_6_3() {
		parser.add("opt", "o", Parser.CHAR);
		
		parser.parse("--opt");
		assertEquals(parser.getChar("opt"), '\0');
	}
	
	@Test
	public void test_15_6_4() {
		parser.add("opt", "o", Parser.INTEGER);
		
		parser.parse("--opt");
		assertEquals(parser.getInteger("opt"), 0);
	}
	
	@Test
	public void test_15_7() {
		parser.add("opt", Parser.INTEGER);
		parser.add("input", Parser.STRING);
		parser.add("optimise","o", Parser.STRING);
		
		parser.parse("--opt 12 --input 1.txt");
		parser.parse("-o");
		assertEquals("1",parser.getInteger("opt"), 12);
		assertEquals("2",parser.getString("input"), "1.txt");
		assertEquals("3",parser.getString("optimise"), "");
	}
	
	
	// OLD tests

	@Test
	public void test_empty_name() {
		parser.add("", Parser.STRING);
		assertEquals(parser.parse("--"), 0); //BUG: cannot parse empty name (spec does not say min length)
	}
	
	@Test
	public void test_empty_shortcut() {
		parser.add("test","", Parser.STRING);
		assertEquals(parser.parse("-"), 0); //BUG: cannot parse empty shortcut (spec does not say min length)
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
		assertEquals(parser.getBoolean("test2"), false); //BUG: default Boolean value should be false
		
		parser.add("test3", Parser.CHAR);
		parser.parse("--test3");
		assertEquals(parser.getChar("test3"), '\0');
		
		parser.add("test4", Parser.INTEGER);
		parser.parse("--test4");
		assertEquals(parser.getInteger("test4"), 0);
		
	}
	
	@Test
	public void test_shortcut_overwrite() {
		parser.add("test1", "t", Parser.STRING);
		parser.parse("-t testValue");
		parser.add("test2", "t", Parser.STRING);
		assertEquals(parser.getString("t"), ""); //BUG: shortcut was overwritten and so value should be reset
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
