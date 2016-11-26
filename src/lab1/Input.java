package lab1;

import java.util.Scanner;

public class Input {
	public static Polynomial polynomial;
	
	public static void inputForm(){
		final String end = "quit";
		polynomial = new Polynomial();
		String result = "";
		while(true) {
			System.out.print(">");
			final Scanner s = new Scanner(System.in);
			final String line = s.nextLine();
			if(line.equals(end)) {
				break;
			}
			if(line.matches("^!simplify.*")) {
				final String ord1 = line.substring(10);
				result = simplifyControl.simplifyPolynomial(ord1);
			} else if(line.matches("^!d/d.*")) {
				final String ord2 = line.substring(4);
				result = derivateControl.derivatePolynomial(ord2);
			} else {
				try{
					result = saveControl.savePolynomial(line);
				} catch(NotPolynomialException e) {
					result = ilegalInputControl.ilegalInput(line);
				}
			}
			Output.OutputForm(result);
		}
	}
	
	public static void main(String[] args)
	{
		inputForm();
	}
}
