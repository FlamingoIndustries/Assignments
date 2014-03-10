Alan Mulhall - 100335911
Software Engineering Project 3 Assignment 4 Report.

Basic overview of my system:
The user input is read in main, passed to
1. A tokenizer which splits it into string elements in a vector, which is passed into:
2. "Oopser" method, which takes in a vector, and converts string elements to FormulaElements. 
If parantheses are found, it will then store the corresponding elements in a second vector, and pass it back to itself.
3. If a vector is fully passed through by the Oopser, it is then passed to the parser which will link up the objects and pass back single object.

So, when a bracketed component is passed back to oopser, assuming there are no more brackets, it will then be passed to the parser, linked up and returned as a 
single object. This object is returned to the inital instance of oopser, and is inserted into the final vector, overwriting the old string elements.

Here are some example inputs that all work ok:
String str = "(2 / (2/4))";	
String str = "cos(2(x+y) + 3)";
String str = "(x+2)(y^3)(z-2)";

I had to change a lot of previous weeks work to get this working, so sorry if any of the older parts are weird. 