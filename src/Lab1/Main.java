package Lab1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args)
	{
		final String end = "quit";
		Polynomial polynomial = new Polynomial();
		while(true)
		{
			System.out.println(">");
			Scanner s = new Scanner(System.in);//get the input String
			String line = s.nextLine();
			if(line.equals(end))
			{
				break;
			}
			Pattern pat1 = Pattern.compile("^!simplify\\p{sapce}.");//to judge whether the order is to simplify
			Matcher mat1 = pat1.matcher(line);
			boolean res1 = mat1.matches();
			
			Pattern pat2 = Pattern.compile("^!d/d.");//to judge whether the order is to differentiate
			Matcher mat2 = pat2.matcher(line);
			boolean res2 = mat2.matches();
			
			if(res1 == true)//if the order is to simplify
			{
				String ord1 = line.substring(10);
				System.out.println(polynomial.simplify(ord1));
			}
			else if(res2 == true)//if the order is to differentiate
			{
				String ord2 = line.substring(4);
				System.out.println(polynomial.derivative(ord2));
			}
			else//if the order is a polynomial
			{
				try
				{
					polynomial = Parse.String2Polynomial(line);
					System.out.println(polynomial.toString());
				}
				catch(NotPolynomialException e)
				{
					System.out.println("This is not a polynomial.");
				}
			}
		}
	}
}
//B1

