import java.awt.event.*;		//For the action event handling
import javax.swing.*;			//For swing components
import java.lang.Exception;		//To catch invalid inputs
import java.awt.Graphics;		//For the painting
import java.awt.Color;			//For the painting

/**
 * ExecuteListener.java
 *
 * A class to validate the user input in the text fields and 
 * paint the function when the "Execute" button is pressed.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/10/20
 */
public class ExecuteListener implements ActionListener, Painter {

	/**
	 * The instance of GraphingCalulator to work with.
	 */
	private GraphingCalculator calculator;
	
	/**
     * The PaintPanel object for drawing the graph.
     */
	private PaintPanel canvas;

	/**
     * The left x-axis bound.
     */
	private double xMin;

	/**
     * The right x-axis bound.
     */
	private double xMax;

	/**
     * The lower y-axis bound.
     */
	private double yMax; 

	/**
     * The left x-axis bound.
     */
    private double yMin;

    /**
     * The width of the canvas.
     */
	private int width;

	/**
     * The height of the canvas.
     */
	private int height;

	/**
     * Whether or not the function is accepted by the parse method (i.e, valid).
     */
	private boolean rightInput;

	/**
     * Whether or not the function field is empty.
     */
	private boolean emptyInput;

	/**
     * Constructor. Sets the input to be valid by default.
     * @param calc This instance of GraphingCalulator to work with.
     */
	public ExecuteListener(GraphingCalculator calc) {
		calculator = calc;
		canvas = calc.canvas();
		width = calc.width();
		height = calc.height();
		rightInput = true;
	}

	/**
     * Validates function input and window bounds.
     * Sets the painter object and calls the paint method.
     * Used by every button on the calculator.
     * @param ae the action event fired by the button.
     */
	public void actionPerformed(ActionEvent ae) {
		try {
			if (xMin > xMax || yMin > yMax) throw new Exception();
			Interpreter.parse(calculator.functionField().getText());	//to test for valid input.
			canvas.setPainter(this);
			canvas.repaint();
		} catch (Exception e) {
			rightInput = false;		//invalidates input that throws an exception.
			canvas.setPainter(this);
			canvas.repaint();
		}
	}

	/**
     * Paints the graph depending on the validity of the function input.
     * Gets the bound values and parses the function to calculate the necessary data.
     * Draws an error message to the canvas if the input is invalid.
     * Calculates the mathematical values and the associated pixels (one by one). 
     * POSTCONDITION: Background, axes, the function, and/an error message are visible.
     * @param g the graphics object used for drawing.
     */
 	public void paint(Graphics g) {

 		//Getting and storing the values for the bounds.
	 		xMax = Double.parseDouble(calculator.xMaxField().getText());
			xMin = Double.parseDouble(calculator.xMinField().getText());
			yMax = Double.parseDouble(calculator.yMaxField().getText());
			yMin = Double.parseDouble(calculator.yMinField().getText());

		//Drawing a backgound.
			g.setColor(Color.WHITE);
			g.fillRoundRect(2, 2, width-3, height-3, 10, 10);
			g.setColor(Color.BLACK);
			g.drawRoundRect(1, 1, width-2, height-2, 10, 10);
	
		//Drawing only axes (no graph or error message) if the function field is empty.
			emptyInput = calculator.functionField().getText().trim().equals("");
			if (emptyInput) {
				drawAxes(g);
				return;
			}

		/*  Drawing an error message and instructions if the function input is invalid.
		   
		    Note: some buttons fires both action listeners simultaneously, so the
		    buttons have to be clicked twice before relevant values are updated by
		    one of the action listeners. */
			
			if (!rightInput) {
				g.setColor(Color.RED);
				g.drawString("Invalid input:", 50, 80);
				g.drawString("Bounds must be numbers or decimals.", 70, 100);
				g.drawString("Function accepts numbers, variable x, and", 70, 130);
				g.drawString("these characters: ( ) + - * / ^", 70, 145);
				g.drawString("Miminum bounds must be less than maximums.", 70, 175);
				g.drawString("Use parentheses in most cases.", 70, 205);
				rightInput = true;
				return;
  			}

  		//Drawing the entire function, pixel by pixel.
 			drawAxes(g);
			g.setColor(Color.BLUE);

			//Determines which x-values are calculated.
			double xStep = (xMax - xMin) / width; 	

 			for (double xValue = xMin; xValue <= xMax; xValue += xStep) {
 				ExprNode function = Interpreter.parse(calculator.functionField().getText());

 				//Calculates and stores the y-value for each x value.
		 		double yValue = function.evaluate(xValue);	

		 		//Stores the associated pixel for each point (x,y).
		 		int xPixelValue = toXPixel (xValue);	
		 		int yPixelValue = toYPixel (yValue); 

		 		//If the y-value is within the canvas
		 		if (yValue > yMin && yValue < yMax)		
		 			g.fillRect(xPixelValue, yPixelValue, 1, 1);
			}
 	}

 	/**
     * Draws the x and y axes if they should be visible.
     * Also draws arrowheads for each axis.
     * @param g the graphics object used for drawing.
     */
	public void drawAxes(Graphics g) {
	 	
	 	//Drawing the x-axis.
	 	if (yMax >= 0 && yMin <= 0) {
	 		g.drawLine(2, toYPixel(0), width - 2, toYPixel(0));
	 		
	 		//Drawing the x-axis arrowheads.
	 		if (xMin < 0) {
	 			g.drawLine(2, toYPixel(0), 10, toYPixel(0) + 10);
	 			g.drawLine(2, toYPixel(0), 10, toYPixel(0) - 10);
	 		}

	 		if (xMax > 0) {
	 			g.drawLine(width - 2, toYPixel(0), width - 10, toYPixel(0) - 10);
	 			g.drawLine(width - 2, toYPixel(0), width - 10, toYPixel(0) + 10);
			}
		}

	 	//Drawing the y-axis.
	 	if (xMax >= 0 && xMin <= 0) {
	 		g.drawLine(toXPixel (0), 2, toXPixel(0), height - 2);
	 		
	 		//Drawing the y-axis arrowheads.
	 		if (yMax > 0) {
	 			g.drawLine(toXPixel (0), 2, toXPixel(0) + 10, 10);
	 			g.drawLine(toXPixel (0), 2, toXPixel(0) - 10, 10);
	 		}

	 		if (yMin < 0) {
	 			g.drawLine(toXPixel (0), height - 2, toXPixel(0) + 10, height - 10);
	 			g.drawLine(toXPixel (0), height - 2, toXPixel(0) - 10, height - 10);
	 		}
	 	}
	}

 	/**
     * Finds the associated column of pixels for each x-value.
     * @param xValue the x-value used for calculation.
     * @return the correct column of pixels for this x-value.
     */
	public int toXPixel(double xValue) {
		double relativeLocation = (xValue - xMin) / (xMax - xMin);
     	return (int)(relativeLocation * width); 
	}

	/**
     * Finds the associated row of pixels for each y-value.
     * @param yValue the x-value used for calculation.
     * @return the correct row of pixels for this y-value.
     */
	public int toYPixel(double yValue) {
		double relativeLocation = (yMax - yValue) / (yMax - yMin);
      	return (int)(relativeLocation * height);
	}

}