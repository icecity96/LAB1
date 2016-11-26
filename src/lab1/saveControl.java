package lab1;

public class saveControl extends Input {
	public static String savePolynomial(String str) throws NotPolynomialException {
		polynomial = new Polynomial();
		polynomial.parse(str);
		String result = polynomial.toString();
		return result;
	}
}
