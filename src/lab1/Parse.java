package lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
	public static Polynomial string2Polynomial(String rawString) throws NotPolynomialException {
		if (rawString.length() < 1) {
			throw new NotPolynomialException();
		}
		Polynomial polynomial = new Polynomial();
		if (rawString.charAt(rawString.length() - 1) == '+' || rawString.charAt(rawString.length() - 1) == '*'
				|| rawString.charAt(rawString.length() - 1) == '-') {
			throw new NotPolynomialException();
		}
		String[] itemString = rawString.replaceAll("-", "\\+-").replaceAll("\\p{Space}*\\+\\p{Space}*", "\\+")
				.split("\\+");
		int coff = 1;
		final Map<String, Integer> variableElement = new HashMap<String, Integer>();
		final String reg = "(?<cof>\\-?\\d*)(?<variable>[a-zA-Z]*)(?:\\^(?<exp>\\d+))?+";
		// String reg = "((?:\\-)?+\\num?+)(?:([a-zA-Z]+)((?:^)\\num)?+)?+";
		final Pattern pattern = Pattern.compile(reg);
		for (String string : itemString) {
			if (string.isEmpty()) {
				continue;
			}
			final String[] temp = string.split("\\*");
			for (final String string2 : temp) {
				final Matcher matcher = pattern.matcher(string2);
				if (matcher.matches()) {
					if (matcher.group("variable").isEmpty()
							&& (matcher.group("cof").isEmpty())) {
						throw new NotPolynomialException();
					}
					if (matcher.group("cof").equals("-") && matcher.group("variable").isEmpty()) {
						throw new NotPolynomialException();
					}
					if (!matcher.group("cof").isEmpty()) {
						if (matcher.group("cof").equals("-")) {
							coff = -1 * coff;
						} else {
							int tempcof = Integer.parseInt(matcher.group("cof"));
							if (matcher.group("exp") != null) {
								tempcof = (int) Math.pow(tempcof, Integer.parseInt(matcher.group("exp")));
							}
							if (matcher.group("variable").isEmpty()) {
								coff *= tempcof;
							} else {
								coff *= Integer.parseInt( matcher.group("cof"));
							}
						}
					}
					if (!matcher.group("variable").isEmpty()) {
						variableElement.put(matcher.group("variable"),
								variableElement.getOrDefault(matcher.group("variable"), 0)
										+ (matcher.group("exp") == null ? 1 : Integer.parseInt(matcher.group("exp"))));
					}

				} else {
					throw new NotPolynomialException();
				}
			}
			Item newItem;
			newItem = new Item();
			newItem.setCoefficient(coff);
			newItem.setVariable(variableElement);
			polynomial.addItem(newItem);
			coff = 1;
			variableElement.clear();
		}
		return polynomial;
	}
}
