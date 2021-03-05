package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

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
	public void bug5() { // 1 point
		parser.add("file","f", Parser.CHAR);
		//parser.add("fix","f", Parser.STRING);
		assertEquals(parser.parse("--file= "), 0);
		assertEquals(parser.getChar("file"), " ");
	}
	
	@Test
	public void bug10() { // 3 points
		parser.add("file","f", Parser.CHAR);
		parser.add("f", Parser.BOOLEAN);
		//parser.add("fix","f", Parser.STRING);
		assertEquals(parser.parse("--file=="), 0);
		assertEquals(parser.getString("f"), "");
	}
	
	@Test
	public void bug13() { // 2 points
		parser.add("file","f", Parser.CHAR);
		parser.add("f", Parser.BOOLEAN);
		//parser.add("fix","f", Parser.STRING);
		assertEquals(parser.parse("-f=value"), 0);
		assertEquals(parser.getString("f"), "");
	}
	
	@Test
	 public void shortcut() {
	  parser.add("output", "out", Parser.STRING);
	  parser.add("out", Parser.STRING);
	  parser.parse("-out=test.txt"); // i assigned a value using shortcut
	  assertEquals(parser.getString("out"),""); // I got the value with shortcut
	 }
}
