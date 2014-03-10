package Formulator;

import java.util.Iterator;
import java.util.Vector;

public class MinusFunctionElement extends FunctionElement {

	public MinusFunctionElement() {

	}

	public MinusFunctionElement(FormulaElement arg1, FormulaElement arg2) {
		getArguments().add(arg1);
		getArguments().add(arg2);
	}

	public String toString() {
		Vector<FormulaElement> set = new Vector<FormulaElement>();
		set = getArguments();
		Iterator<FormulaElement> it = set.iterator();
		String str = "";
		double total = 0;
		int i = 0;
		double flag = 0;
		while (it.hasNext()) {

			FormulaElement element = it.next();

			if (i == 0) { // if the first arg is a constant, store it for later
				if (element instanceof ConstantElement) {
					flag = ((ConstantElement) element).getValue();
				}
			}

			if (element instanceof ConstantElement) {
				total = total + ((ConstantElement) element).getValue();
			}

			if (isFunction(element) == true) {
				if ((element instanceof MultipleFunctionElement)
						|| (element instanceof DivideFunctionElement)) {
					str = str + "(" + element.toString() + ")";
				} else {
					str = str + element.toString();
				}
			}

			if (it.hasNext() == true) {
				str = str + " - ";
			}
			i++;

		}

		if (flag == 0) { // if the first arg was a variable

			if (total != 0) {
				ConstantElement x = new ConstantElement(total);
				String ints = x.toString();
				str = str + ints;
			}
			return str;
		}

		else { // if the first arg was a constant, treat it as positive

			total = total - flag;

			if (total != 0) {
				ConstantElement x = new ConstantElement(total);
				String ints = x.toString();
				ConstantElement y = new ConstantElement(flag);
				String first = y.toString();
				str = first + " - " + str + ints;
			}

			return str;
		}

	}

}
