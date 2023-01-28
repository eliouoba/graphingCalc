/**
 * Interpreter.java.
 *
 * Class to generate ExprNode trees based on a given function input.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/9/2020
 */
public class Interpreter {

    /**
     * Parses a string and turns it into an ExprNode tree.
     * @param input The string to parse.
     * @return The root of the ExprNode tree.
     */
    public static ExprNode parse(String input) {
        
        String nodes[] = ExprStringSlicer.slice(input); 
        ExprNode subnode = null;
        
        if (nodes.length == 1) {
            if (nodes[0].equals("x".toLowerCase()))    //nodes[0] is the variable x
                subnode = new Variable();
            else                                       //nodes[0] is an integer
                subnode = new Number(nodes[0]);         
        } else                                         //nodes[0] is an operator
            subnode = new Operation(nodes[1], parse(nodes[0]), parse(nodes[2]));
        return subnode;
    }

}
