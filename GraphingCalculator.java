import javax.swing.*;       //For the swing components
import java.awt.*;          //For the layout managers
import java.awt.event.*;    //For firing action events

/**
 * GraphingCalculator.java
 *
 * Graphing calculator program. Graphs polynonials and other simple funtions.
 *
 * @author eliouoba
 * CSCI 235, Wheaton College, Fall 2020
 * Project 7
 * 12/10/20
 */
public class GraphingCalculator {

    /**
     * The text field containing the function input.
     */
    private JTextField functionField;

    /**
     * The text field containing the left x-axis bound.
     */
    private JTextField xMinField;

    /**
     * The text field containing the right x-axis bound.
     */
    private JTextField xMaxField;

    /**
     * The text field containing the lower y-axis bound.
     */
    private JTextField yMinField;

    /**
     * The text field containing the upper y-axis bound.
     */
    private JTextField yMaxField;

    /**
     * The PaintPanel object for drawing the graph.
     */
    private PaintPanel canvas;

    /**
     * The amount by which the pan buttons will pan.
     */
    private double panStepValue;

    /**
     * To access the function field in the ActionListener classes.
     */
    public JTextField functionField() { return functionField; }

    /**
     * To access the left x-axis bound in the ActionListener classes.
     */
    public JTextField xMinField() { return xMinField; }

    /**
     * To access the right x-axis bound in the ActionListener classes.
     */
    public JTextField xMaxField() { return xMaxField; }
   
    /**
     * To access the lower y-axis bound in the ActionListener classes.
     */
    public JTextField yMinField() { return yMinField; }

    /**
     * To access the upper y-axis bound in the ActionListener classes.
     */
    public JTextField yMaxField() { return yMaxField; }

    /**
     * To access the canvas in the ActionListener classes.
     */
    public PaintPanel canvas() { return canvas; }

    /**
     * To access the step value for panning.
     */
    public double panStepValue() { return panStepValue; }

    /**
     * To access the width of the canvas in the ActionListener classes.
     */
    public int width() { return canvas.getWidth(); }

    /**
     * To access the height of the canvas in the ActionListener classes.
     */
    public int height() { return canvas.getHeight(); }

    /**
     * To mutate the step value for panning.
     * PRECONDITION: The pan set value will not reduce below 0.5 after this invocation.
     * POSTCONDITION: The pan step value has been incremented +/- 0.5.
     * @param d The value by which the pan step value is incremented.
     */
    public void incrementPan(double d) { panStepValue += d; }
    
    /**
    * Constructor. Creates this instance of GraphingCalculator.
    * This is formatted as a constructor instead of a main method
    * so that the ActionListener classes can access 
    * certain components in the window, referenced through
    * instance variables of this instance of GraphingCalculator.
    */
    public GraphingCalculator() {
           
        /*  Creating a canvas for drawing the graph and placing it 
            in the center of a new BorderLayout panel for spacing. 
            Canvas dimensions are ajustable. */

            canvas = new PaintPanel(350, 350);
            JPanel graphPanel = new JPanel(new BorderLayout());
            
            //Space left of the canvas.
            graphPanel.add(new JLabel("     "), BorderLayout.WEST); 

            //Space above the canvas.
            graphPanel.add(new JLabel(" "), BorderLayout.NORTH);  

            graphPanel.add(canvas, BorderLayout.CENTER);
            
        //Creating the function components and organizing them in a new panel. 
            JLabel functionLabel = new JLabel("f(x) = ");
            functionField = new JTextField(30);     
            JPanel functionPanel = new JPanel(new FlowLayout());
            functionPanel.add(functionLabel);
            functionPanel.add(functionField);
            
        /*  Creating the components for the window bounds and 
            organizing them in a new panel. */
            xMinField = new JTextField(5); 
            xMinField.setText("-10");
            yMinField = new JTextField(5);
            yMinField.setText("-10");
            xMaxField = new JTextField(5);
            xMaxField.setText("10");
            yMaxField = new JTextField(5);
            yMaxField.setText("10");    

            JPanel boundsPanel = new JPanel(new GridLayout(2,5));  //The new panel
            boundsPanel.add(new JLabel("X Min:"));
            boundsPanel.add(xMinField);
            boundsPanel.add(new JLabel());               //For spacing
            boundsPanel.add(new JLabel("X Max:"));
            boundsPanel.add(xMaxField);
            boundsPanel.add(new JLabel("Y Min:"));
            boundsPanel.add(yMinField);
            boundsPanel.add(new JLabel());               //For spacing
            boundsPanel.add(new JLabel("Y Max:"));
            boundsPanel.add(yMaxField);
            
        //Creating the buttoms to pan the window and organizing them in a new panel.
            panStepValue = 2.0;
            JPanel panPanel = new JPanel(new GridLayout(4,3));
            JLabel panLabel = new JLabel("   Pan ");
            JLabel panStep = new JLabel("  Step:  ");
            JButton right = new JButton(">");
            right.addActionListener(new WindowListener(this, "right", panStepValue));
            right.addActionListener(new ExecuteListener(this));

            JButton left = new JButton("<");
            left.addActionListener(new WindowListener(this, "left", panStepValue));
            left.addActionListener(new ExecuteListener(this));

            JButton up = new JButton("^");
            up.addActionListener(new WindowListener(this, "up", panStepValue));
            up.addActionListener(new ExecuteListener(this));

            JButton down = new JButton("v");
            down.addActionListener(new WindowListener(this, "down", panStepValue));
            down.addActionListener(new ExecuteListener(this));
         
            JButton increase = new JButton("+");
            increase.addActionListener(new WindowListener(this, "increase", panStepValue));
            increase.addActionListener(new ExecuteListener(this));
            
            JButton decrease = new JButton("-");
            decrease.addActionListener(new WindowListener(this, "decrease", panStepValue));
            decrease.addActionListener(new ExecuteListener(this));

            panPanel.add(new JLabel());     //For spacing
            panPanel.add(up);
            panPanel.add(new JLabel());     //For spacing
            panPanel.add(left);
            panPanel.add(panLabel);
            panPanel.add(right);
            panPanel.add(new JLabel());     //For spacing
            panPanel.add(down);
            panPanel.add(new JLabel());     //For spacing
            panPanel.add(panStep);
            panPanel.add(increase);
            panPanel.add(decrease);

        //Creating the buttoms to zoom and organizing them in a new panel.

            JPanel zoomPanel = new JPanel(new GridLayout(4,1));
            
            JLabel zoomLabel = new JLabel("     Zoom");
            zoomPanel.add(zoomLabel);

            JButton zoomIn = new JButton("+");
            zoomIn.addActionListener(new WindowListener(this, "in", panStepValue));
            zoomIn.addActionListener(new ExecuteListener(this));
            zoomPanel.add(zoomIn);

            JButton zoomOut = new JButton("-");
            zoomOut.addActionListener(new WindowListener(this, "out", panStepValue));
            zoomOut.addActionListener(new ExecuteListener(this));
            zoomPanel.add(zoomOut);

            JButton reset = new JButton("Reset");
            reset.addActionListener(new WindowListener(this, "reset", panStepValue));
            reset.addActionListener(new ExecuteListener(this));
            zoomPanel.add(reset);

        //Creating some sample functions to graph and organizing them in a new panel.

            JPanel examplePanel = new JPanel(new GridLayout(8,1));

            JLabel example = new JLabel("Try out these example functions or type in your own.");
            examplePanel.add(example);

            JButton linear = new JButton("Linear: f(x) = 0.5x + 2");
            linear.addActionListener(new ExampleListener(this, "linear"));
            linear.addActionListener(new ExecuteListener(this));
            examplePanel.add(linear); 

            JButton quadratic = new JButton("Quadratic: f(x) = -0.1(x^2) + 2");
            quadratic.addActionListener(new ExampleListener(this, "quadratic"));
            quadratic.addActionListener(new ExecuteListener(this));
            examplePanel.add(quadratic); 

            JButton cubic = new JButton("Cubic: f(x) = 0.5(x^3) - 2X - 1");
            cubic.addActionListener(new ExampleListener(this, "cubic"));
            cubic.addActionListener(new ExecuteListener(this));
            examplePanel.add(cubic); 

            JButton rational1 = new JButton("Rational: f(x) = -3 / (x-2)");
            rational1.addActionListener(new ExampleListener(this, "rational1"));
            rational1.addActionListener(new ExecuteListener(this));
            examplePanel.add(rational1); 

            JButton rational2 = new JButton("Rational: f(x) = (x^2 / x^2 - 1) + 1");
            rational2.addActionListener(new ExampleListener(this, "rational2"));
            rational2.addActionListener(new ExecuteListener(this));
            examplePanel.add(rational2); 

            JButton radical = new JButton("Radical: f(x) = (x + 6)^(0.5) - 3");
            radical.addActionListener(new ExampleListener(this, "radical"));
            radical.addActionListener(new ExecuteListener(this));
            examplePanel.add(radical); 

            JButton exponential = new JButton("Exponential: f(x) = -2^(x-3) + 5");
            exponential.addActionListener(new ExampleListener(this, "exponential"));
            exponential.addActionListener(new ExecuteListener(this));
            examplePanel.add(exponential); 

        /*  Creating a button to draw the graph, and adding it to a new label
            along with the pan label and the zoom label.  */
            JButton executeButton = new JButton("Execute");
            executeButton.addActionListener(new ExecuteListener(this));
            JPanel executePanZoom = new JPanel(new FlowLayout());
            executePanZoom.add(executeButton);
            executePanZoom.add(new JLabel());      //For spacing
			executePanZoom.add(new JLabel());      //For spacing
			executePanZoom.add(new JLabel());      //For spacing
            executePanZoom.add(panPanel);
			executePanZoom.add(new JLabel());      //For spacing
			executePanZoom.add(new JLabel());      //For spacing
			executePanZoom.add(new JLabel());      //For spacing
            executePanZoom.add(zoomPanel);
         
        /*  Adding all the panels to a new panel that spans the right half  
            of the window. The left half is occupied by the graphPanel.   */
    
            JPanel rightPanel = new JPanel(new FlowLayout()); 
            
            rightPanel.add(examplePanel);
            rightPanel.add(new JLabel("                           "));  //for spacing
            rightPanel.add(functionPanel); 
            rightPanel.add(boundsPanel);
            rightPanel.add(executePanZoom);

        //Creating and initializing the program window and adding its components.
            JFrame window = new JFrame("Graphing calculator");
            window.setLayout(new GridLayout(1,2));
            window.setSize(800, 520);
            window.setLocation(100, 100); 
            window.add(graphPanel);
            window.add(rightPanel);
    	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    window.setVisible(true);
    }

    /**
     * Creating and running this instance of GraphingCalculator.
     */
    public static void main(String[] args) {
        GraphingCalculator calculator = new GraphingCalculator();
    }

}
