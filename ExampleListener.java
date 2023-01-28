import java.awt.event.*;		//For the action event handling
import javax.swing.*;			//For swing components

/**
 * ExampleListener.java.
 *
 * A class to provide examples of functions to graph.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/10/20
 */
public class ExampleListener implements ActionListener {

	/**
	 * The instance of GraphingCalulator to work with.
	 */
	private GraphingCalculator calculator;

	/**
	 * Represents the function associated with the button pressed.
	 */
	private String button;

	/** 
	 * Constructor.
	 * Associates the instance variables with components of the calculator.
	 * @param calc This instance of GraphingCalulator to work with.
	 * @param button Represents the function associated with the button pressed.
	 */
	public ExampleListener(GraphingCalculator calc, String button) {
		calculator = calc;
		this.button = button;
	}

	/** 
	 * Writes new text in the calculator function field.
	 * @param function A string representing the function to be added.
	 */
	public void setFunction(String function) {
		calculator.functionField().setText(function);
	}

	/**
	 * Writes new functions in the function field according to the button pressed. 
     * @param ae the action event fired by the button.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (button.equals("linear")) setFunction("(((1/2)*x) + 2)");
		if (button.equals("quadratic")) setFunction("(((0 - (1/10)) * (x^2)) + 2)");
		if (button.equals("cubic")) setFunction("(((1/2) * (x^3)) - ((2*x) - 1))");
		if (button.equals("rational1")) setFunction("((0 - 3) / (x-2))");
		if (button.equals("rational2")) setFunction("(((x^2) / ((x^2) - 1)) + 1)");
		if (button.equals("radical")) setFunction("(((x + 6)^(1/2)) - 3)");
		if (button.equals("exponential")) setFunction("((0 - (2^(x - 3))) + 5)");
		
	}

}