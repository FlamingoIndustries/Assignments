package Formulator;
 class ConstantElement extends FormulaElement {
	private double x;
	
	public ConstantElement(double y) {	
	x = y;
	}
	
	public double getValue(){
		return x;
	}
	
	public String toString(){
		String a = "";

		if (x % 1 == 0){		//if double could be an int, return an int
			int y = (int)x;
			x = y;
			a = a+y;
		}
		
		else{
			a = a+x;
		}
		return a;
		
	}

}
