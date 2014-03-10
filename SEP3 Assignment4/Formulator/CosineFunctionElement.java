package Formulator;

public class CosineFunctionElement extends FunctionElement {

	public CosineFunctionElement(){
		
	}
	
	public CosineFunctionElement(FormulaElement arg){
		getArguments().add(arg);
	}
	
	public void addArgument(FormulaElement arg) { //add arguments
		
		if (getArguments().size() > 0){
			System.out.println("Error. Element may only have one argument.");
		}
		
		else{
			getArguments().add(arg);

		}
	}
	
	public String toString(){
		String str = "";
		if(getArguments().size() > 0){
			str = "cos("+getArguments().elementAt(0).toString() +")";
			}
		return str;
		
	}
}
