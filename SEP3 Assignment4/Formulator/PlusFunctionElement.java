package Formulator;

import java.util.Iterator;
import java.util.Vector;

public class PlusFunctionElement extends FunctionElement {

	public PlusFunctionElement() {

	}

	public PlusFunctionElement(FormulaElement arg1, FormulaElement arg2) {
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
		int flag = 0;

		while (i < set.size()) {

			FormulaElement element = it.next();

			if (i == 0) {
				if (element instanceof ConstantElement) {
					flag = 1;
				}

			}

			if (element instanceof ConstantElement) {
				total = total + ((ConstantElement) element).getValue();
			}

			if (isFunction(element) == true) {

				str = str + element.toString();
			}

			if (it.hasNext() == true) {
				str = str + " + ";
			}
			i++;
		}

		if (total != 0) {

		}

		if (flag == 1) {
			ConstantElement x = new ConstantElement(total);
			String ints = x.toString();
			str = ints + str;
		}

		if (flag == 0 && total != 0) {
			ConstantElement x = new ConstantElement(total);
			String ints = x.toString();
			str = str + ints;
		}

		return str;

	}

}
