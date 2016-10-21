package lab1;

import java.util.Scanner;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		final String end = "quit";
		Polynomial polynomial = new Polynomial();
		while (true) {
			System.out.print(">");
			final Scanner s = new Scanner(System.in);
			final String line = s.nextLine();
			if (line.equals(end)) {
				break;
			}
			if (line.matches("^!simplify.*")) {
				final String ord1 = line.substring(10);
				System.out.println(polynomial.simplify(ord1));
			} else if (line.matches("^!d/d.*")) {
				final String ord2 = line.substring(4);
				System.out.println(polynomial.derivative(ord2));
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
