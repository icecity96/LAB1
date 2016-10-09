package Lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polynomial {
	public Polynomial() {
		// TODO Auto-generated constructor stub
		polynomial = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getPolynomial() {
		return polynomial;
	}
	public void setPolynomial(ArrayList<Item> polynomial) {
		this.polynomial = new ArrayList<Item>(polynomial);
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
	
	public String derivative(String var) {
		Polynomial tempPolynomial = new Polynomial();
		boolean hasVar = false;
		for (Item item : polynomial) {
			if (item.getVariable().get(var) != null) {
				hasVar = true;
				int cof = item.getCoefficient()*item.getVariable().get(var).intValue();
				Map<String, Integer> variables = new HashMap<String,Integer>(item.getVariable());
				if (variables.get(var).intValue() == 1) {
					variables.remove(var);
				} else {
					variables.put(var, variables.get(var).intValue()-1);
				}
				Item newItem = new Item();
				newItem.setCoefficient(cof);
				newItem.setVariable(variables);
				tempPolynomial.addItem(newItem);
			}
		}
		if ( hasVar == false) {
			return "No such var "+var;
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
			int 	value = Integer.parseInt(matcher.group("value"));
			boolean hasVar = false;
			for (Item item : tempPolynomial.getPolynomial()) {
				if (item.getVariable().get(var) != null) {
					hasVar = true;
					int cof = (int) Math.pow(value, item.getVariable().get(var).intValue());
					item.getVariable().remove(var);
					item.setCoefficient(cof*item.getCoefficient());
				}
			}
			if ( hasVar == false) {
				return "No such var: "+var;
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
		String result = new String();
		boolean firstItem = true;
		for (Item item : polynomial) {
			if ( firstItem || item.getCoefficient() < 0) {
				firstItem = false;
				result += item.toString();
			} else {
				result += ("+" + item.toString());
			}	
		}
		return result;
	}
	
	private ArrayList<Item> polynomial;
}

//just tag
class NotPolynomialException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1204978569819425448L;
	
}

//B1


//C4


//B2