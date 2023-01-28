/** 
 * Operation.java.
 *
 * A program to evaluate expressions involving operators.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/9/2020
 */
public class Operation implements ExprNode {

	/**
	 * The operator used between two numbers/variables.
	 */
	private String operator;

	/**
	 * The first (left) operand.
	 */
	private ExprNode left;

	/**
	 * The second (right) operand.
	 */
	private ExprNode right;


	/**
	 * Constructor. Provides information to be stored by the instance variables.
	 * @param text a string from the function input to be parsed.
	 * @param leftOperand The first operand.
	 * @param rightOperand The second operand.
	 */
    public Operation (String text, ExprNode leftOperand, ExprNode rightOperand) {
   		operator = text;
   		left = leftOperand;
   		right = rightOperand;
    }

	/**
	* Evaluates the expression depending on the type of operator.
	* PRECONDITION: The function input is valid.
	* @param x The value of all variables in the function (not used here).
	* @return The evaluated expression, as a double. 
	*/
	public double evaluate(double x) {
		switch (operator) {
			case "+":	return left.evaluate(x) + right.evaluate(x);
			case "-": 	return left.evaluate(x) - right.evaluate(x);
			case "*":	return left.evaluate(x) * right.evaluate(x);
			case "/":	return left.evaluate(x) / right.evaluate(x);
			default: 	return Math.pow(left.evaluate(x), right.evaluate(x));
	   	}
 	} 
}

