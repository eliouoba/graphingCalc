/** 
 * Variable.java.
 *
 * A program to evaluate expressions involving variables.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/9/2020
 */
public class Variable implements ExprNode {

	/**
	* No calculation needed; just returns the value of the variable.
	* PRECONDITION: The function input is valid.
	* @param x The value of all variables in the function.
	* @return The value of x.
	*/
	public double evaluate(double x) {
		return x;
	}
}

