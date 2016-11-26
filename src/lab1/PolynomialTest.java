package lab1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PolynomialTest {
	private Polynomial polynomial = new Polynomial(); 
	@Before
	public void setUp() throws Exception {
		 polynomial.parse("3x*z^2");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDerivative() {
		assertEquals("No such var y",polynomial.derivative("y"));
	}
	@Test
	public void testDerivative2() {
		assertEquals("3z^2",polynomial.derivative("x"));
	}
	@Test
	public void testDerivative3() {
		assertEquals("6x*z",polynomial.derivative("z"));
	}
}
