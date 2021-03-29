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
	
	@Test
	public void bug1() { // 2 points
		parser.add("", Parser.STRING);
		assertEquals(0, parser.parse("--"));
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
		parser.add("file", "f", Parser.STRING);

		assertEquals(0, parser.parse("-f=___"));
	}
	
	@Test
	public void bug9() {
		parser.add("file", Parser.STRING);

		assertEquals(0, parser.parse("--file=AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
	}
	
	@Test
	public void bug10() { // 3 points
		parser.add("file","f", Parser.CHAR);
		parser.add("f", Parser.BOOLEAN);

		assertEquals(0, parser.parse("--file=="), 0);
		assertEquals("", parser.getString("f"));
	}
	
	@Test
	public void bug11() { // 3 points
		parser.add("output", "oUt", Parser.STRING);
	  	parser.add("oUt", "out", Parser.STRING);
	  	
	  	parser.parse("-oUt=1.txt -out=12");
	  	assertEquals("1.txt", parser.getString("output"));
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

		assertEquals(0, parser.parse("-f=value"));
		assertEquals("", parser.getString("f"));
	}
}
