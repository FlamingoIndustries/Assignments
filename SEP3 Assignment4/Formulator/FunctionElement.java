package Formulator;

import java.util.Vector;

//Allows operators to be assigned values to act upon.

public abstract class FunctionElement extends FormulaElement {
	
	
	private Vector<FormulaElement> arguments = new Vector<FormulaElement>();
	
	public FunctionElement(){
		
	}
	
	public FunctionElement(FormulaElement arg1, FormulaElement arg2){
		arguments.add(arg1);
		arguments.add(arg2);
	}
	
	
	public void addArgument(FormulaElement arg) { //add arguments
		
		arguments.add(arg);
	
		
	}
	
	public Vector<FormulaElement> getArguments() { //get argument
		return arguments;	
	}

}
