package st;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

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
		assertEquals(0, parser.parse("--list=e"));
		System.out.println(parser.getCharacterList("list"));
		assertEquals(new ArrayList<Character>(Arrays.asList('e')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test1() {
		parser.add("list", "l", Parser.STRING);
		parser.add("l", Parser.STRING);
		assertEquals(0, parser.parse("--l=eck -l=wow"));
		assertEquals(new ArrayList<Character>(Arrays.asList('e','c','k')), parser.getCharacterList("l"));
	}
	
	@Test
	public void test2() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(0, parser.parse("--list= "));
		assertEquals(new ArrayList<Character>(), parser.getCharacterList("list"));
	}
	
	@Test
	public void test3() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(0, parser.parse("--list=+ni=-pp.123"));
		assertEquals(new ArrayList<Character>(Arrays.asList('n','i','p','p','.','1','2','3')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test4() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(0, parser.parse("--list=TEST1.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','1','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test5() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(0, parser.parse("--list=-arbValue"));
		assertEquals(new ArrayList<Character>(), parser.getCharacterList("list"));
	}
	
	@Test
	public void test6() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=test123-.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','1','2','3','.','t','x','t')), parser.getCharacterList("list"));
	}

	@Test
	public void test7() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=test123-.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','1','2','3','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test8() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=test1-4.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','1','2','3','4','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test9() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=test4-1.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','4','3','2','1','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test10() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=testa-d.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','a','b','c','d','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test11() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=testd-a.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','d','c','b','a','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test12() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=test7-c.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','7','8','9','a','b','c','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test13() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=testa-d-b.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','a','b','c','d','d','c','b','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test14() {
		parser.add("list", Parser.STRING);
		assertEquals(0, parser.parse("--list=test#@&.txt"));
		assertEquals(new ArrayList<Character>(Arrays.asList('t','e','s','t','.','t','x','t')), parser.getCharacterList("list"));
	}
	
	@Test
	public void test15() {
		parser.add("list", "l", Parser.STRING);
		assertEquals(new ArrayList<Character>(), parser.getCharacterList("list"));
	}
}
