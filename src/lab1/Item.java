package lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Item {
	private int coefficient;
	private Map<String, Integer> variable;

	public Item() {
		variable = new HashMap<String, Integer>();
	}

	public final int getCoefficient() {
		return coefficient;
	}

	public final Map<String, Integer> getVariable() {
		return variable;
	}

	public final void setVariable(final Map<String, Integer> variable) {
		this.variable = new HashMap<String, Integer>(variable);
	}

	public final void setCoefficient(final int coefficient) {
		this.coefficient = coefficient;
	}

	public final void add(final Item item) {
		coefficient += item.coefficient;
	}

	public final boolean equals(final Item item) {
		if (item.variable.size() != variable.size()) {
			return false;
		} else {
			boolean flag = false;
			int tmp1;
			int tmp2;
			for (Map.Entry<String, Integer> entry : variable.entrySet()) {
				if (item.variable.containsKey(entry.getKey())) {
					tmp1 = entry.getValue();
					tmp2 = item.variable.get(entry.getKey());
					if (tmp1 == tmp2) {
						flag = true;
						continue;
					} else {
						flag = false;
						break;
					}
				} else {
					flag = false;
					break;
				}
			}
			return flag;
		}
	}

	@Override
	public final String toString() {
		String result = new String();
		if (variable.isEmpty()) {
			result += coefficient;
		} else if (coefficient != 1 && coefficient != -1) {
			result += coefficient;
		} else if (coefficient == -1) {
			result += '-';
		}
		boolean first = true;
		Set<Map.Entry<String, Integer>> set = variable.entrySet();
		for (Map.Entry<String, Integer> entry : set) {
			String var = entry.getKey();
			int cof = entry.getValue().intValue();
			if (first == true) {
				first = false;
			} else {
				result += "*";
			}
			if (cof == 1) {
				result += var;
			} else {
				result += (var + "^" + cof);
			}
		}
		return result;
	}
}
