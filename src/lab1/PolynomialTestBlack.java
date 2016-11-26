package lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PolynomialTestBlack {
	
	private Polynomial polynomial = new Polynomial();
	
	@Before
	public void setUp() throws Exception {
		String line = "x+y+3*weight";
		polynomial.parse(line);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimplify() {
		String str = "!simplify x=1";
		String supposeRes = "1+y+3weight";
		String result = polynomial.simplify(str);
		assertEquals(supposeRes, result);
	}

	@Test
	public void testSimplify2() {
		String str = "!simplify";
		String supposeRes = "x+y+3weight";
		String result = polynomial.simplify(str);
		assertEquals(supposeRes, result);
	}
	
	@Test
	public void testSimplify3() {
		String str = "!simplify x=1，weight=3";
		String supposeRes = "1+y+9";
		String result = polynomial.simplify(str);
		assertEquals(supposeRes, result);
	}
	
	@Test
	public void testSimplify4() {
		String str = "!simplify x2=1";
		String supposeRes = "x+y+3weight";
		String result = polynomial.simplify(str);
		assertEquals(supposeRes, result);
	}
	
	@Test
	public void testSimplify5() {
		String str = "!simplify x=d";
		String supposeRes = "x+y+3weight";
		String result = polynomial.simplify(str);
		assertEquals(supposeRes, result);
	}
	
	@Test
	public void testSimplify6() {
		String str = "!simplify z=3";
		String supposeRes = "No such var: z";
		String result = polynomial.simplify(str);
		assertEquals(supposeRes, result);
	}
}

