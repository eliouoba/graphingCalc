/**
 * ExprNode.java
 *
 * An interface to define the operation evaluate() on
 * nodes in an expression tree.
 * 
 * @author Thomas VanDrunen
 * CSCI 235, Wheaton College
 * Project 7
 * 
 */
public interface ExprNode {

	/**
	* evaluates the expression depending on the 
	* type of the node: number, operator, or variable.
	*/
    double evaluate(double x);

}