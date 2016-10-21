package lab1;

import java.util.Scanner;

public class Main {
	@SuppressWarnings("resource")
	public static void main(final String[] args) throws NotPolynomialException {
		final String end = "quit";
		Polynomial polynomial = new Polynomial();
		while (true) {
			System.out.print(">");
			final Scanner s = new Scanner(System.in);
			final String line = s.nextLine();
			if (line.equals(end)) {
				break;
			}
			int len = line.length();
			if (line.matches("^!simplify.*")) {
				if (len <= 10) {
					System.out.println(polynomial.simplify(""));
					continue;
				}
				final String ord1 = line.substring(10);
				String temp = polynomial.simplify(ord1);
				System.out.println(temp);
				polynomial = Parse.string2Polynomial(temp);
			} else if (line.matches("^!d/d.*")) {
				if (len <= 4) {
					System.out.println("No such var");	
					continue;
				}
				final String ord2 = line.substring(4);
				String temp = polynomial.derivative(ord2);
				System.out.println(temp);
				polynomial = Parse.string2Polynomial(temp);
			} else {
				try {
					polynomial = Parse.string2Polynomial(line);
					System.out.println(polynomial.toString());
				} catch (NotPolynomialException e) {
					System.out.println("This is not a polynomial.");
				}
			}
		}
	}
}
