package Formulator;

import java.util.StringTokenizer;
import java.util.Vector;

public abstract class FormulaElement {
	

	public static void main(String[] args){

	//Some strings I used for testing:
	String str = "(2 / (2/4))";	
	//String str = "cos(2(x+y) + 3)";
	//String str = "(x+2)(y^3)(z-2)";

	System.out.println(oopser(tokenizer(str)));

	}
	
	public String toString(){
		
		return null;
		
	}
	public static Vector<Object> tokenizer(String text){	//take input string, return string tokens
		Vector <Object> tokens = new Vector <Object>();
		StringTokenizer tokenizer = new StringTokenizer(text, "+-/^() \t", true);
		
		
		while(tokenizer.hasMoreTokens() == true){
			String sub = tokenizer.nextToken();
			if ((Character.isDigit(sub.charAt(0)) == true) && (Character.isLetter(sub.charAt(sub.length()-1) ) )){
				int i = 0;
				while(Character.isDigit(sub.charAt(i))){
					i++;
				}
				String suba = sub.substring(0, i);
				String subb = sub.substring(i);
				tokens.add(suba);
				tokens.add(subb);
			}
			else if(Character.isSpaceChar(sub.charAt(0)) == true){
				
			}
			else{
				
						tokens.add(sub);
					}

		}
		//split up input, return in vector tokens	
		return tokens;
	}
	
	
	
	public static Object oopser(Vector<Object> tokens){
		Vector <Object> subtokens = new Vector <Object>();	

		
		int j = 0;
		
		
		while(j<tokens.size()){
			if (isFunction(tokens.get(j)) == true){
				//Skip an object if it's already a FunctionElement
			}
			
			else{
				

			String element = (String) tokens.get(j);

			String cos = "cos";
			String sin = "sin";
			String plus = "+";
			String div = "/";
			String min = "-";
			String pow = "^";
			String lpar = "(";
			String rpar = ")";
			
			
			if (isNumeric(element) == true){
				double a = Double.parseDouble(element);
				ConstantElement x = new ConstantElement(a);

				tokens.setElementAt(x, j);
			}
			else if(element.equals(plus) == true){
				PlusFunctionElement x = new PlusFunctionElement();
				tokens.setElementAt(x, j);
			}
			
			else if(element.equals(div) == true){
				DivideFunctionElement x = new DivideFunctionElement();
				tokens.setElementAt(x, j);
			}
			else if(element.equals(cos) == true){
				CosineFunctionElement x = new CosineFunctionElement();
				tokens.setElementAt(x, j);
			}
			
			else if(element.equals(sin) == true){
				SineFunctionElement x = new SineFunctionElement();
				tokens.setElementAt(x, j);
			}
			else if(element.equals(pow) == true){
				PowerFunctionElement x = new PowerFunctionElement();
				tokens.setElementAt(x, j);
			}
			else if(element.equals(min) == true){
				MinusFunctionElement x = new MinusFunctionElement();
				tokens.setElementAt(x, j);

			}
			else if(element.equals(lpar)){
				
				//Lpar found
				int y = j+1;
				int t1 = 1;
				int flag = 0;
				
				if(j>0){	//if the lpar is not first in input token vector
					
					if (isFunction(tokens.get(j-1)) == false ){						
						flag = 1;		//if there is no (+,-,/) before, put an implicit multiplication object before it.	
					}
					
					if (isFunction(tokens.get(j-1)) == true ){
						if(tokens.get(j-1) instanceof SineFunctionElement || tokens.get(j-1) instanceof CosineFunctionElement){
						//don't do it if element before is sin or cos.
						}
						
						else{
							if ( ((FunctionElement) tokens.get(j-1)).getArguments().size() > 0  ){	
						    //if a full function element is before, then do put in implicit multiplication element.
							flag = 1;
							}
						}
					}
				}
				
				while(t1 != 0 && y<tokens.size()){
					if(tokens.get(y).equals(lpar) == true){
						t1++;		//increase counter of pars
					}
					
					if((tokens.get(y).equals(rpar) == true) && (t1==1)){
						t1--;
						tokens.removeElementAt(y);		//if matching rpar found, remove and break
						break;					
					}
					
					if(tokens.get(y).equals(rpar) == true && t1>=2){
						t1--;
						subtokens.add(tokens.get(y));	//if not matching rpar, add to subtoken vector
						tokens.removeElementAt(y);
					}				
										
					else {						
						subtokens.add(tokens.get(y));	//otherwise, add token to subtoken vector
						tokens.removeElementAt(y);						
					}				
				}
				
//				String out = "";
//				for(int f = 0; f<subtokens.size(); f++){			
//					out+= "<" + subtokens.get(f).toString() + "," + subtokens.get(f).getClass().getName() + ">";
//				}
//				System.out.println(out);
				
				//Send off subtoken vector to oopser recursively, and add in the MFE if needed.
				if (flag == 1){
				tokens.setElementAt(oopser(subtokens), j);
				MultipleFunctionElement x = new MultipleFunctionElement();		
				tokens.add(j, x);
				}
				
				if(flag == 0){
                tokens.setElementAt(oopser(subtokens), j);
				}
				
				//once subtoken vector was passed over, clear it. (might not be needed)
				subtokens.clear();
				flag = 0;
				
				j=0;
			
			}
			else if ((element.equals(cos) == false) && (element.equals(sin) == false) && (element.equals(plus) == false) && 
					(element.equals(min) == false) && (element.equals(div) == false) && (element.equals(pow) == false)){
				//Must be Variable
				VariableElement x = new VariableElement(element);

				tokens.setElementAt(x, j);
				}
			}
			j++;
		}
		
		//Send the input vector to the parser which will join them up.
		return parseFormula(tokens);
	}
	
	public static Object parseFormula(Vector<Object> tokens){

		
		while(tokens.size() > 1){
		//COSINE & SINE (BRACKETS)
		int l = 0;
		while(l < tokens.size() - 1){
			Object token =  tokens.get(l);
			Object arg = tokens.get(l + 1);
					
				if(token instanceof CosineFunctionElement){
					CosineFunctionElement x = new CosineFunctionElement((FormulaElement) arg);
					tokens.removeElementAt(l + 1);
					tokens.setElementAt(x, l);

				}
				if(token instanceof SineFunctionElement){
					SineFunctionElement x = new SineFunctionElement((FormulaElement) arg);
					tokens.removeElementAt(l + 1);
					tokens.setElementAt(x, l);
						}
				l++;
			}
					
				
		
		//POWER FUNCTION
		l=0;
		while(l < tokens.size() && tokens.size() > 2){
			Object token =  tokens.get(l);
			
			if ((token instanceof PowerFunctionElement)){
				if (  ((FunctionElement) token).getArguments().size() > 0 ){
					
				}
				else{
				Object arg1 = tokens.get(l - 1);
				Object arg2 = tokens.get(l + 1);
				PowerFunctionElement x = new PowerFunctionElement((FormulaElement) arg1, (FormulaElement) arg2);
				tokens.removeElementAt(l + 1);
				tokens.removeElementAt(l);
				tokens.setElementAt(x, l-1);
				}
			}
			l++;
		}

		

		//MULTIPLICATION OF VARIABLES AND CONSTANTS
		int k = 0;
		while (k < tokens.size()){
			
			//if a blank MFE was found (usually from bracket parsing)
			if(tokens.get(k) instanceof MultipleFunctionElement && k>0){
				FormulaElement arg1 = (FormulaElement) tokens.get(k-1);
				FormulaElement arg2 = (FormulaElement) tokens.get(k+1);
				MultipleFunctionElement x = new MultipleFunctionElement((FormulaElement)arg1, (FormulaElement)arg2);
				tokens.setElementAt(x, k-1);
				tokens.removeElementAt(k+1);
				tokens.removeElementAt(k);							
			}
			else{
			k++;
			}
		}
		
		k=0;
		if (tokens.size() == 2){
				//if there are only 2 objects left, assume an implicit multiplier
				FormulaElement arg1 = (FormulaElement) tokens.get(k);
				FormulaElement arg2 = (FormulaElement) tokens.get(k+1);
				MultipleFunctionElement x = new MultipleFunctionElement((FormulaElement)arg1, (FormulaElement)arg2);
				tokens.setElementAt(x, k);
				tokens.removeElementAt(k+1);		
		}
		
		//older multiplier code for implicit variable/constant multipliers
		k=0;
		while(k < tokens.size() - 1){
			Object token = tokens.get(k);
			Object token2 = tokens.get(k+1);
			
			if ((token instanceof ConstantElement) && (token2 instanceof ConstantElement)){
				MultipleFunctionElement x = new MultipleFunctionElement((ConstantElement)token, (ConstantElement)token2);
				tokens.setElementAt(x, k);
				tokens.removeElementAt(k+1);
				}
			else if ((token instanceof ConstantElement) && (token2 instanceof VariableElement)){
				MultipleFunctionElement x = new MultipleFunctionElement((ConstantElement)token, (VariableElement)token2);
				tokens.setElementAt(x, k);
				tokens.removeElementAt(k+1);
				}
			else if ((token instanceof VariableElement) && (token2 instanceof VariableElement)){
				MultipleFunctionElement x = new MultipleFunctionElement((VariableElement)token, (VariableElement)token2);
				tokens.setElementAt(x, k);
				tokens.removeElementAt(k+1);
				}
			k++;
			}						
		l=0;
		
		//DIVISION 
		while(l < tokens.size() -1){
			Object token =  tokens.get(l);
			if ((token instanceof DivideFunctionElement)){
				Object arg1 = tokens.get(l - 1);
				Object arg2 = tokens.get(l + 1);
				DivideFunctionElement x = new DivideFunctionElement((FormulaElement) arg1, (FormulaElement) arg2);
				tokens.removeElementAt(l + 1);
				tokens.removeElementAt(l);
				tokens.setElementAt(x, l-1);
			}
			l++;
		}
		
		l=1;
		// PLUS
		while(l < tokens.size() - 1){
			Object token =  tokens.get(l);
			if ((token instanceof PlusFunctionElement)){
				FormulaElement arg1 = (FormulaElement) tokens.get(l - 1);
				FormulaElement arg2 = (FormulaElement) tokens.get(l + 1);			
				PlusFunctionElement x = new PlusFunctionElement();
				x.addArgument(arg1);
				x.addArgument(arg2);
				tokens.removeElementAt(l + 1);
				tokens.removeElementAt(l);
				tokens.setElementAt(x, l-1);
			}
			
			l++;
		}
		
		l=1;
		// MINUS
		while(l < tokens.size() - 1){
			Object token =  tokens.get(l);
			if ((token instanceof MinusFunctionElement)){
				Object arg1 = tokens.get(l - 1);
				Object arg2 = tokens.get(l + 1);
				MinusFunctionElement x = new MinusFunctionElement((FormulaElement) arg1, (FormulaElement) arg2);
				tokens.removeElementAt(l + 1);
				tokens.removeElementAt(l);
				tokens.setElementAt(x, l-1);
			}

			l++;
		}
	}
		
		//Uncomment below out to see recursive steps
		//System.out.println(tokens.get(0).toString());
		return tokens.get(0);
	}
	
	
	public static boolean isNumeric(String str)
	{
	    return str.matches("-?\\d+(.\\d+)?");
	}
	
	public static boolean isFunction(Object element){
		if (element instanceof MinusFunctionElement || element instanceof PlusFunctionElement || 
				element instanceof MultipleFunctionElement || element instanceof DivideFunctionElement ||
				element instanceof PowerFunctionElement || element instanceof SineFunctionElement ||
				element instanceof CosineFunctionElement || element instanceof VariableElement){
			return true;
		}

		return false;
		
	}
}
