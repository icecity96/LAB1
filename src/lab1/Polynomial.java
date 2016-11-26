package lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polynomial {
	public Polynomial() {
		// TODO Auto-generated constructor stub
		polynomial = new ArrayList<Item>();
	}
	
	public List<Item> getPolynomial() {
		return polynomial;
	}
	public void setPolynomial(List<Item> polynomial) {
		for (Item item : polynomial) {
			Item item2 = new Item();
			item2.setCoefficient(item.getCoefficient());
			item2.setVariable(item.getVariable());
			this.polynomial.add(item2);
		}
	}
	
	public void addItem(Item aItem) {
		if (polynomial.isEmpty()) {
			polynomial.add(aItem);
		} else {
			for (Item item : polynomial) {
				if ( item.equals(aItem)) {
					item.add(aItem);
					return;
				}
			}
			polynomial.add(aItem);
		}
	}
	
	public void parse(String rawString) throws NotPolynomialException {
		if (rawString.length() < 1) {
			throw new NotPolynomialException();
		}
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
			polynomial.add(newItem);
			coff = 1;
			variableElement.clear();
		}
	}
	
	public String derivative(String var) {
		Polynomial tempPolynomial = new Polynomial();
		boolean hasVar = false;
		for (final Item item : polynomial) {
			if (item.getVariable().get(var) != null) {
				hasVar = true;
				int cof = item.getCoefficient() 
						* item.getVariable().get(var).intValue();
				Map<String, Integer> variables = new HashMap<String, Integer>(item.getVariable());
				if (variables.get(var).intValue() == 1) {
					variables.remove(var);
				} else {
					variables.put(var, variables.get(var).intValue() - 1);
				}
				Item newItem = new Item();
				newItem.setCoefficient(cof);
				newItem.setVariable(variables);
				tempPolynomial.addItem(newItem);
			}
		}
		if (hasVar == false) {
			return "No such var " + var;
		}
		return tempPolynomial.toString();
	}
	public String simplify(String string) {
		Polynomial tempPolynomial = new Polynomial();
		tempPolynomial.setPolynomial(polynomial);
		String reg = "(?<var>[a-zA-Z]+)=(?<value>\\d+)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			String var = matcher.group("var");
			int value = Integer.parseInt(matcher.group("value"));
			boolean hasVar = false;
			for (Item item : tempPolynomial.getPolynomial()) {
				if (item.getVariable().get(var) != null) {
					hasVar = true;
					int cof = (int) Math.pow(value, item.getVariable().get(var).intValue());
					item.getVariable().remove(var);
					item.setCoefficient(cof * item.getCoefficient());
				}
			}
			if (hasVar == false) {
				return "No such var: " + var;
			}
		}
		Polynomial result = new Polynomial();
		for (Item item : tempPolynomial.getPolynomial()) {
			result.addItem(item);
		}
		return result.toString();
	}
	@Override
	public String toString() {
		String result = "";
		boolean firstItem = true;
		for (Item item : polynomial) {
			if (firstItem || item.getCoefficient() < 0) {
				firstItem = false;
				result += item.toString();
			} else {
				result += ("+" + item.toString());
			}	
		}
		return result;
	}
	private List<Item> polynomial;
}

//just tag
class NotPolynomialException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1204978569819425448L;
	
}
