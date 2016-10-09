package lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//词法解析
public class Parse {
	public static  Polynomial String2Polynomial(String rawString) throws NotPolynomialException
	{
		Polynomial polynomial = new Polynomial();
		if ( rawString.charAt(rawString.length()-1) == '+' ||
				rawString.charAt(rawString.length()-1) == '*' ||
					rawString.charAt(rawString.length()-1) == '-') {
			throw new NotPolynomialException();
		}
		String[] ItemString = rawString.replaceAll("-", "\\+-").replaceAll("\\p{Space}*\\+\\p{Space}*", "\\+").split("\\+");
		int coff = 1;
		Map<String, Integer> VariableElement = new HashMap<String,Integer>();
		String reg = "(?<cof>\\-?\\d*)(?<variable>[a-zA-Z]*)(?:\\^(?<exp>\\d*))?+";
		//String reg = "((?:\\-)?+\\num?+)(?:([a-zA-Z]+)((?:^)\\num)?+)?+";
		Pattern pattern = Pattern.compile(reg);
		for (String string : ItemString) {
			if (string.isEmpty()) {
				continue;
			}
			String[] temp = string.split("\\*");
			for (String string2 : temp) {
				Matcher matcher = pattern.matcher(string2);
				if ( matcher.matches()) {
					//Item 系数
					if ( !matcher.group("cof").isEmpty()) {
						if ( matcher.group("cof").equals("-")) {
							coff = -coff;
						} else {
							coff *= Integer.parseInt( matcher.group("cof"));
						}
					}
					if (!matcher.group("variable").isEmpty()) {
						VariableElement.put(matcher.group("variable"), 
								VariableElement.getOrDefault(matcher.group("variable"), 0)+
								(matcher.group("exp")==null?1:Integer.parseInt(matcher.group("exp"))));
					}
				
				} else {
					throw new NotPolynomialException();
				}
			}
			Item newItem = new Item();
			newItem.setCoefficient(coff);
			newItem.setVariable(VariableElement);
			polynomial.addItem(newItem);
			coff = 1;
			VariableElement.clear();
		}
		return polynomial;
	}
}
