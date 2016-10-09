package lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
//项类
public class Item {
	public Item() {
		variable = new HashMap<String,Integer>();
		// TODO Auto-generated constructor stub
	}
	public Map<String, Integer> getVariable() {
		return variable;
	}
	public void setVariable(Map<String, Integer> variable) {
		this.variable = new HashMap<String,Integer>(variable);
	}
	public int getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	
	@Override
	public String toString() {
		String result = new String();
		if (coefficient != 1 && coefficient != -1) {
			result += coefficient;
		} else if (coefficient == -1) {
			result += "-";
		}
		boolean first = true;
		Set<Map.Entry<String, Integer>> set = variable.entrySet();
		for ( Map.Entry<String, Integer> entry : set) {
			String var = entry.getKey();
			int		cof = entry.getValue().intValue();
			if ( first == true) {
				first = false;
			} else {
				result += "*";
			}
			if ( cof == 1) {
				result += var;
			} else {
				result += (var + "^" + cof);
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(Object object){
		if (!(object instanceof Item)) {
			return false;
		}
		Item item = (Item)object;
		return variable.equals(item.getVariable());
	}
	
	public void add(Object object) {
		if (object instanceof Item) {
			Item item = (Item)object;
			if ( this.equals(item)) {
				coefficient += item.getCoefficient();
			}
		}
	}
	
	private Map<String, Integer> variable; 
	private int coefficient;
}
