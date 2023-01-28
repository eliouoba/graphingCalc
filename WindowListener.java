import java.awt.event.*;		//For the action event handling
import javax.swing.*;			//For swing components

/**
 * WindowListener.java.
 *
 * A class to manipulate the graphing window by panning and zooming.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/10/20
 */
public class WindowListener implements ActionListener {

	/**
	 * The instance of GraphingCalulator to work with.
	 */
	private GraphingCalculator calculator;

	/**
	 * Represents the button pressed.
	 */
	private String button;

	/**
     * The amount by which the pan buttons will pan.
     */
	private double panStepValue;

	/** 
	 * Constructor. 
	 * Associates all of the instance variables with components of the calculator.
	 * @param calc This instance of GraphingCalulator to work with
	 * @param button Represents the button pressed.
	 * @param panStepValue The amount by which the pan buttons will pan.
	 */
	public WindowListener (GraphingCalculator calc, String button, double panStepValue) {
		calculator = calc;
		this.button = button;
		this.panStepValue = panStepValue;
	}

	/**
	 * Pans the graphing window in various directions depending on the button clicked.
	 * Allows the user to adjust how much the window pans.
	 * Zooms the graphing window in or out at a fixed increment of 2.
	 * Resets the window bounds to their original setting.
     * @param ae the action event fired by the button.
	 */
	public void actionPerformed(ActionEvent ae) {

		//Getting and storing the values for the bounds.
			double xMax = Double.parseDouble(calculator.xMaxField().getText());
			double xMin = Double.parseDouble(calculator.xMinField().getText());
			double yMax = Double.parseDouble(calculator.yMaxField().getText());
			double yMin = Double.parseDouble(calculator.yMinField().getText());

		/* Panning in certain directions. Sets the text in the bounds automatically,
		   and then the ExecuteListener repaints the function with the new bounds. */
		if (button.equals("right")) {
			calculator.xMinField().setText((xMin + calculator.panStepValue()) + " ");
			calculator.xMaxField().setText((xMax + calculator.panStepValue()) + " ");
		}

		if (button.equals("left")) {
			calculator.xMinField().setText((xMin - calculator.panStepValue()) + " ");
			calculator.xMaxField().setText((xMax - calculator.panStepValue()) + " ");
		}

		if (button.equals("up")) {
			calculator.yMinField().setText((yMin + calculator.panStepValue()) + " ");
			calculator.yMaxField().setText((yMax + calculator.panStepValue()) + " ");
		}

		if (button.equals("down")) {
			calculator.yMinField().setText((yMin - calculator.panStepValue()) + " ");
			calculator.yMaxField().setText((yMax - calculator.panStepValue()) + " ");
		}

		/*  Changing the step values for the pan. Only works if it will keep the pan
		    step above 0. Note that panStepValue starts at 2. */
			if (button.equals("increase")) 
				calculator.incrementPan(0.5);

			if (button.equals("decrease") && calculator.panStepValue() > 0.5) 
				calculator.incrementPan(-0.5); 

		//Zooming in. Only works if it will keep xMin < xMax.
		if (button.equals("in") && (xMax - xMin > 4)) {
			calculator.xMinField().setText((xMin + 2) + " ");
			calculator.xMaxField().setText((xMax - 2) + " ");
			calculator.yMinField().setText((yMin + 2) + " ");
			calculator.yMaxField().setText((yMax - 2) + " ");
		}

		//Zooming out.
		if (button.equals("out")) {
			calculator.xMinField().setText((xMin - 2) + " ");
			calculator.xMaxField().setText((xMax + 2) + " ");
			calculator.yMinField().setText((yMin - 2) + " ");
			calculator.yMaxField().setText((yMax + 2) + " ");
		}

		//Resetting the zoom to x: [-10,10] and y: [-10, 10].
		if (button.equals("reset")) {
			calculator.xMinField().setText((-10) + " ");
			calculator.xMaxField().setText((10) + " ");
			calculator.yMinField().setText((-10) + " ");
			calculator.yMaxField().setText((10) + " ");
		}
	}

}