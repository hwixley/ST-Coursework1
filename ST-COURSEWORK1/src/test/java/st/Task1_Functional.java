package st;

import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class Task1_Functional {
	
private Parser parser;
	
	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	/*@Test
	public void bug1() { // 2 points
		parser.add("", Parser.STRING);
		assertEquals(0, parser.parse("--")); //BUG: cannot parse empty name (spec does not say min length)
	}
	
	@Test (expected = RuntimeException.class)
	public void bug2() { // 1 point
		parser.add("1test", "t", Parser.STRING);
		assertNotEquals(0, parser.parse("--1test"));
	}
	
	@Test
	public void bug3() { // 1 point
		parser.add("output","o", Parser.STRING);
		parser.add("filename", Parser.STRING);
		
		assertEquals(0, parser.parse("--filename 1.txt -o 2.txt"), 0);
		assertEquals("1.txt", parser.getString("filename"));
		assertEquals("2.txt", parser.getString("output"));
	}
	
	@Test
	public void bug4() {
		parser.add("optimise", "O", Parser.BOOLEAN);
		parser.parse("-O=0");
		assertEquals(false, parser.getBoolean("optimise"));
	}
	
	@Test
	public void bug5() { // 1 point
		parser.add("file","f", Parser.CHAR);
		//parser.add("fix","f", Parser.STRING);
		assertEquals(0, parser.parse("--file= "));
		assertEquals(" ", parser.getChar("file"));
	}
	
	@Test
	public void bug6() { // 1 point
		parser.add("option","o", Parser.BOOLEAN);

		parser.parse("--option=9rfg");
		assertEquals(true, parser.getBoolean("option"));

		parser.parse("--option=00");
		assertEquals(true, parser.getBoolean("option"));
	}
	
	@Test
	public void bug7() {
		parser.add("optimise", "oo", Parser.INTEGER);
		assertEquals(0, parser.parse("--optimise=-1"));
		assertEquals(-1, parser.getInteger("optimise"));
	}
	
	@Test
	public void bug8() {
		
	}
	
	@Test
	public void bug9() {
		
	}
	
	@Test
	public void bug10() { // 3 points
		parser.add("file","f", Parser.CHAR);
		parser.add("f", Parser.BOOLEAN);
		//parser.add("fix","f", Parser.STRING);
		assertEquals(0, parser.parse("--file=="), 0);
		assertEquals("", parser.getString("f"));
	}
	
	@Test
	public void bug11() { // 3 points
		parser.add("output", "oUt", Parser.STRING);
	  	parser.add("oUt", "out", Parser.STRING);
	  	parser.parse("-oUt=1.txt -out=12"); // i assigned a value using shortcut
	  	assertEquals("1.txt", parser.getString("output")); // I got the value with shortcut
	  	assertEquals("12", parser.getString("oUt"));
	}
	
	@Test
	public void bug12() {
		parser.add("file", Parser.STRING);

		assertEquals(0, parser.parse(""));
	}
	
	@Test
	public void bug13() { // 2 points
		parser.add("file","f", Parser.CHAR);
		parser.add("f", Parser.BOOLEAN);
		//parser.add("fix","f", Parser.STRING);
		assertEquals(0, parser.parse("-f=value"));
		assertEquals("", parser.getString("f"));
	}*/
	
	@Test
	public void bug() {
		parser.add("file", Parser.STRING);

		assertEquals(0, parser.parse(""));
		assertEquals("9rfg", parser.getString(""));
	}
	
	/*@Test
	public void shortcut() {
		//parser.add("filename", Parser.STRING);
		parser.add("output", "o", Parser.STRING);
		parser.add("o_9", Parser.BOOLEAN);
		assertEquals(parser.parse("--o =1.txt -o=2.txt"), 0);
		//assertEquals(parser.parse("--o"), 0);
		//parser.add("output", "O", Parser.STRING);
		
		//assertEquals(parser.parse("-o=1.txt --filename=2.txt"), 0);
		assertEquals(parser.getString("o "), "1.txt");
		assertEquals(parser.getString("output"), "2.txt");

		//assertEquals(parser.parse("-O=O"), 0); // i assigned a value using shortcut
		//parser.add("O","optimise", Parser.BOOLEAN);
		//assertEquals(parser.getString(""), ""); // I got the value with shortcut
		
		//assertEquals(parser.getString("oUt"), "12");
	}
	
	@Test
	public void bug() {
		parser.add("F", Parser.STRING);
		parser.add("f", Parser.STRING);
		parser.parse("--F value --f");
		assertEquals(parser.getString("F"), "value");
		assertEquals(parser.getString("f"), "");
	}
	@Test
	public void test_11() {
		parser.add("output","o", Parser.STRING);
		parser.add("filename", Parser.STRING);

		assertEquals(parser.parse("--filename=1.txt -o=2.txt"), 0);
		assertEquals(parser.getString("filename"), "1.txt");
		assertEquals(parser.getString("output"), "2.txt");
	}

	@Test
	public void test_13_shortcut() {
		parser.add("output","o", Parser.STRING);
		parser.parse("-o=value");
		assertEquals(parser.getString("output"), parser.getString("o"));
	}

	@Test
	public void test_13_1_overwriteName() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o=niceVal");

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

		parser.parse("--t=val1 -t=val2");

		assertEquals(parser.getString("test"), "val2");
		assertEquals(parser.getString("t"), "val1");
	}

	@Test
	public void test_13_5_boolean_cases() {
		parser.add("option","o", Parser.BOOLEAN);

		//TRUE
		parser.parse("-o=9rfg");
		assertEquals(parser.getBoolean("option"), true);

		parser.parse("-o=00");
		assertEquals(parser.getBoolean("option"), true);

		parser.parse("-o");
		assertEquals(parser.getBoolean("option"), true);

		parser.parse("-o=ffalse");
		assertEquals(parser.getBoolean("option"), true);

		//FALSE
		parser.parse("-o=0");
		assertEquals(parser.getBoolean("option"), false);

		parser.parse("-o=fAlsE");
		assertEquals(parser.getBoolean("option"), false);
	}

	@Test (expected = RuntimeException.class)
	public void test_invalidType() {
		parser.add("option", Parser.CHARLIST);
	}

	public void test_14_1_overwriteName() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output=niceVal");

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
		parser.add("t","tt", Parser.STRING);

		parser.parse("--t=val1 -t=val2");

		assertEquals(parser.getString("test"), "val2");
		assertEquals(parser.getString("t"), "val1");
	}

	@Test
	public void test_14_5_boolean_cases() {
		parser.add("option","o", Parser.BOOLEAN);

		//TRUE
		parser.parse("--option=9rfg");
		assertEquals(parser.getBoolean("option"), true);

		parser.parse("--option=00");
		assertEquals(parser.getBoolean("option"), true);

		parser.parse("--option");
		assertEquals(parser.getBoolean("option"), true);

		parser.parse("--option=ffalse");
		assertEquals(parser.getBoolean("option"), true);

		//FALSE
		parser.parse("--option=0");
		assertEquals(parser.getBoolean("option"), false);

		parser.parse("--option=fAlsE");
		assertEquals(parser.getBoolean("option"), false);
	}

	@Test
	public void test_15_1() {
		parser.add("name", "n", Parser.STRING);
		assertNotEquals("15_1: should be invalid input",parser.parse("--n=value"),0);
	}

	@Test
	public void test_15_2() {
		parser.add("name", "n", Parser.STRING);
		assertNotEquals("15_2: should be invalid input",parser.parse("-name=value"),0);
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
		parser.parse("--opt=value");
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
		parser.parse("--opt=0 --opt=1 --opt=24 --opt=69 --opt=2");
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

		parser.parse("--opt=12 --input=1.txt");
		parser.parse("-o");
		assertEquals("1",parser.getInteger("opt"), 12);
		assertEquals("2",parser.getString("input"), "1.txt");
		assertEquals("3",parser.getString("optimise"), "");
	}*/
}
