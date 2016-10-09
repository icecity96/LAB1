package lab1;

import java.beans.Expression;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String inputString = new String();
		final String QUIT = new String("quit");
		Polynomial polynomial = new Polynomial();
		while ( !inputString.equals(QUIT) ) {
			System.out.print(">");
			Scanner scanner = new Scanner(System.in);
			inputString = scanner.nextLine();
			if ( !inputString.matches("!.*") ) {
				polynomial = expression(inputString);
				System.out.println(polynomial.toString());
				System.out.println(polynomial.derivative("z"));
				System.out.println(polynomial.simplify("x=4,y=3"));
			}
		}
	}
	private static Polynomial expression(String string){
		try {
			return Parse.String2Polynomial(string);
		} catch (NotPolynomialException e) {
			System.out.println("多项式错误");
			return null;
		}
	}

}
