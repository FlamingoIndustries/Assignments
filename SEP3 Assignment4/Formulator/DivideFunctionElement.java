package Formulator;

import java.util.Iterator;
import java.util.Vector;

public class DivideFunctionElement extends FunctionElement {
	
	public DivideFunctionElement(){
		
	}
	
	public DivideFunctionElement(FormulaElement arg1, FormulaElement arg2){
		getArguments().add(arg1);
		getArguments().add(arg2);
	}
	
	public String toString(){
		Vector<FormulaElement> set = new Vector<FormulaElement>();
		set = getArguments();
		Iterator<FormulaElement> it = set.iterator();
		String str = "";
		int i = 0;
		while(i < set.size()){

			FormulaElement element = it.next();
			
			
			if (element instanceof ConstantElement){
				str = str + element.toString();
			}
			
			if (isFunction(element) == true){
				if ((element instanceof MultipleFunctionElement) || (element instanceof DivideFunctionElement)){
					str = str + "(" + element.toString() + ")" ;
				}
				else{
					str = str +  element.toString() ;
				}

			}
			
			if (element instanceof VariableElement){
				str = str + element.toString(); //can leave x + 0 (total);
			}
			
			if (it.hasNext() == true){
				str = str + " / ";
			}
			i++;
		}
		
		 
		
		return str;
		
		
	}
}
