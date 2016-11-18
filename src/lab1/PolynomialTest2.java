package lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PolynomialTest2 {
	private Polynomial polynomial = new Polynomial(); 
	@Before
	public void setUp() throws Exception {
		polynomial = Parse.string2Polynomial("3x*z^2");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDerivative() {
		assertEquals("3z^2",polynomial.derivative("x"));
	}
}