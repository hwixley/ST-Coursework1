package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Task3_TDD_1 {

private Parser parser;	
	
	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	@Test
	public void test0() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(parser.parse("--list=e"), 0);
		System.out.println(parser.getCharacterList("list"));
		assertEquals(parser.getCharacterList("list"), {'e'});
	}
	
	@Test
	public void test1() {
		parser.add("list", "l", Parser.STRING);
		parser.add("l", Parser.STRING);
		assertEquals(parser.parse("--l=eck -l=wow"), 0);
		assertEquals(parser.getCharacterList("l"), {'e','c','k'});
	}
	
	@Test
	public void test2() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(parser.parse("--list="), 0);
		assertEquals(parser.getCharacterList("list"), {});
	}
	
	@Test
	public void test3() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(parser.parse("--list=+ni=-pp.123"), 0);
		assertEquals(parser.getCharacterList("list"), {'n','i','p','p','.','1','2','3'});
	}
	
	@Test
	public void test4() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(parser.parse("--list=TEST1.txt"), 0);
		assertEquals(parser.getCharacterList("list"), {'t','e','s','t','1','.','t','x','t'});
	}
	
	@Test
	public void test5() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(parser.parse("--list="), 0);
		assertEquals(parser.getCharacterList("list"), {});
	}

}
