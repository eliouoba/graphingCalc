/** 
 * Number.java.
 *
 * A program to evaluate expressions of integer values.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/9/2020
 */
public class Number implements ExprNode {

	/**
	 * The value of the integer used in the expression
	 */
	private int value;

	/**
     * Constructor.
     * @param text a string from the function input to be parsed.
     */
	public Number (String text) {
		value = Integer.parseInt(text);
	} 

	/**
	* No calculation needed; just returns the value of the variable.
	* PRECONDITION: The function input is valid.
	* @param x The value of all variables in the function (not used here).
	* @return The value of the integer used here.
	*/
	public double evaluate(double x) {
		return value;
	}
	
}


