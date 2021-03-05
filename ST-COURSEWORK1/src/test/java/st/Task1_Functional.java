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
	public void bug3() { // 1 point
		parser.add("output","o", Parser.STRING);
		parser.add("filename", Parser.STRING);
		
		assertEquals(parser.parse("--filename 1.txt -o 2.txt"), 0);
		assertEquals(parser.getString("filename"), "1.txt");
		assertEquals(parser.getString("output"), "2.txt");
	}
	
	@Test (expected = RuntimeException.class)
	public void bug2() { // 1 point
		parser.add("1test", "t", Parser.STRING);
	}
	
	@Test
	public void bug1() { // 2 points
		parser.add("", Parser.STRING);
		assertEquals(parser.parse("--"), 0); //BUG: cannot parse empty name (spec does not say min length)
	}
	
	
	
	@Test
	public void bug() {
		parser.add("F", Parser.STRING);
		parser.add("f", Parser.STRING);
		parser.parse("--F value --f");
		assertEquals(parser.getString("F"), "value");
		assertEquals(parser.getString("f"), "");
	}
}
