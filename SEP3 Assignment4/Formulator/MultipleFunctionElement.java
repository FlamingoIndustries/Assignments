package Formulator;

import java.util.Iterator;
import java.util.Vector;

public class MultipleFunctionElement extends FunctionElement {

	public MultipleFunctionElement() {

	}

	public MultipleFunctionElement(FormulaElement arg1, FormulaElement arg2) {
		getArguments().add(arg1);
		getArguments().add(arg2);
	}

	public String toString() {
		/*
		 * Vector<FormulaElement> set = new Vector<FormulaElement>(); set =
		 * getArguments(); Iterator<FormulaElement> it = set.iterator();
		 * FormulaElement element = null; String str = ""; while(it.hasNext()){
		 * 
		 * 
		 * FormulaElement element2 = it.next();
		 * 
		 * if (element instanceof ConstantElement && element2 instanceof
		 * ConstantElement){ str = str + "x"; } else{ str = str; }
		 * 
		 * str = str + element2.toString(); element = element2;
		 * 
		 * 
		 * }
		 */

		Vector<FormulaElement> set = new Vector<FormulaElement>();
		set = getArguments();
		Iterator<FormulaElement> it = set.iterator();
		FormulaElement element = null;

		String str = "";
		double total = 1;
		while (it.hasNext()) {
			element = it.next();
			if (element instanceof ConstantElement) {
				total = total * ((ConstantElement) element).getValue();
			}

			if (element instanceof FunctionElement) {
				if ((element instanceof MultipleFunctionElement)
						|| (element instanceof DivideFunctionElement)) {
					str = str + element.toString();
				} else {
					str = str + "(" + element.toString() + ")";
				}
			}

			if (element instanceof VariableElement) {
				str = str + element.toString();
			}

		}
		if (total != 1) {
			ConstantElement x = new ConstantElement(total);
			String ints = x.toString();
			str = ints + str;
			return str;
		} else {
			return str;
		}
	}

}
