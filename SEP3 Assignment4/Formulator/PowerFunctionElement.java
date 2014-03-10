package Formulator;

import java.util.Iterator;
import java.util.Vector;

public class PowerFunctionElement extends FunctionElement {

	public PowerFunctionElement() {

	}

	public PowerFunctionElement(FormulaElement arg1, FormulaElement arg2) {
		getArguments().add(arg1);
		getArguments().add(arg2);
	}

	public void addArgument(FormulaElement arg) { // add arguments

		if (getArguments().size() > 1) {
			System.out.println("Error. Element may only have one argument.");
		}

		else {
			getArguments().add(arg);

		}
	}
	
	public String toString() {
		Vector<FormulaElement> set = new Vector<FormulaElement>();
		set = getArguments();
		String str = "";
		FormulaElement element = set.get(0);
		FormulaElement element2 = set.get(1);
		if(set.size() < 2){
			System.out.println("Error: Not enough arguments for power function.");
		}
		else{
			if (element instanceof ConstantElement && element2 instanceof ConstantElement){
				double total = 0;
				double num1, num2;
				num1 = ((ConstantElement) element).getValue();
				num2 = ((ConstantElement) element2).getValue();
				total = Math.pow(num1,num2);
				if (total % 1 == 0){
					int y = (int)total;
					str = str + y;
				}
				else{
					str = str + total;
				}
			}
			else{
				str = str + element.toString() + "^" + element2.toString();
			}
		}
		return str;
		
	}
}
