package Formulator;

public class VariableElement extends FormulaElement{
private String name;
private double value;
	
	public VariableElement(String inputname) {	
	name = inputname;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double inputvalue){
		value = inputvalue;
	}
	
	public String toString(){
		
		return name;
		
	}

}
