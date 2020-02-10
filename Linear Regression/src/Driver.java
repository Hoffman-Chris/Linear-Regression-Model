import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

/**
 * This class will create a window that takes input of a users ACT score
 * and gives their predicted first semester college GPA.
 * 
 * @author Chris Hoffman
 */
public class Driver implements ActionListener
{
	private Data data;
	private JFrame inputWindow;
	private JTextField input;
	private JLabel output;
	private JButton button;
	
	/**
	 * simple main that creates instance of driver
	 * class and runs its methods
	 */
	public static void main( String[] args ) 
	{
		Driver driver = new Driver();
		driver.initDataSet();
		driver.makeInputWindow();
	}
	
	/**
	 * creates window for user input
	 * and displays predictions
	 * 
	 */
	private void makeInputWindow()
	{
		int winWidth = 800;
    	int winHeight = 300;
    	int graphicHeight = 30;
    	
        inputWindow = new JFrame();
        inputWindow.setLayout( null );
        inputWindow.setSize( winWidth, winHeight );
        inputWindow.setLocation( 50, 50 );
        inputWindow.setTitle( "Linear Regression Demonstration" );
        inputWindow.setVisible( true );
        inputWindow.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        inputWindow.setResizable( false );
        
        input = new JTextField();
        input.setBounds( 150, graphicHeight, winWidth - 200, graphicHeight );
        inputWindow.add( input, 0 );
        
        output = new JLabel();
        output.setBounds( 150, graphicHeight * 5, winWidth - 200, graphicHeight );
        output.setBackground( Color.white );
        output.setOpaque( true );
        
        inputWindow.add( output, 0 );
        
        JLabel inLabel = new JLabel( "ACT Score" );
        inLabel.setBounds( 20, graphicHeight, 105, graphicHeight );
        
        inLabel.setHorizontalAlignment( SwingConstants.CENTER );
        inputWindow.add( inLabel,0 );
        
        JLabel outLabel = new JLabel( "Predicted GPA" );
        outLabel.setBounds( 20, graphicHeight * 5, 105, graphicHeight );
        outLabel.setHorizontalAlignment( SwingConstants.CENTER );
        inputWindow.add( outLabel, 0 );

        button = new JButton();
        button.setLocation( 350, graphicHeight * 7 );
        button.setSize( 105, graphicHeight );
        button.setText( "Predict" );
        button.addActionListener( this );
        inputWindow.add( button,0 );
        
        inputWindow.repaint();
        
        //solve the system of equations to get X hat
		data.solveSystem();
	}
	
	/**
	 * initializes the data set
	 */
	private void initDataSet()
	{      
        //ACT scores of sample set
        double x_1 = 35, x_2 = 18, x_3 = 26, x_4 = 30, x_5 = 30,
        		x_6 = 27, x_7 = 22, x_8 = 21, x_9 = 33, x_10 = 28,
        		x_11 = 18, x_12 = 19, x_13 = 21, x_14 = 29, x_15 = 33,
        		x_16 = 35, x_17 = 23, x_18 = 25, x_19 = 26, x_20 = 28,
        		x_21 = 24, x_22 = 32, x_23 = 27, x_24 = 35, x_25 = 31;
        
        //first semester college GPA of the sample set
        double y_1 = 4.00, y_2 = 2.00, y_3 = 3.5, y_4 = 3.50, y_5 = 3.75,
        		y_6 = 3.25, y_7 = 2.75, y_8 = 3.00, y_9 = 4.00, y_10 = 3.25,
				y_11 = 2.00, y_12 = 2.25, y_13 = 2.75, y_14 = 3.5, y_15 = 3.75,
        		y_16 = 4.00, y_17 = 3.00, y_18 = 3.75, y_19 = 3.00, y_20 = 3.5,
        		y_21 = 2.75, y_22 = 3.25, y_23 = 3.5, y_24 = 4.00, y_25 = 3.5;
        
       double[][] xData = 
        	  { { 1, x_1 },
        		{ 1, x_2 },
        		{ 1, x_3 },
        		{ 1, x_4 },
        		{ 1, x_5 },
        		{ 1, x_6 },
        		{ 1, x_7 },
        		{ 1, x_8 },
        		{ 1, x_9 },
        		{ 1, x_10 }, 
        		{ 1, x_11 },
        		{ 1, x_12 },
        		{ 1, x_13 },
        		{ 1, x_14 },
        		{ 1, x_15 },
        		{ 1, x_16 },
        		{ 1, x_17 },
        		{ 1, x_18 },
        		{ 1, x_19 },
        		{ 1, x_20 },
        		{ 1, x_21 },
        		{ 1, x_22 },
        		{ 1, x_23 },
        		{ 1, x_24 },
        		{ 1, x_25 },};

        
        double[][] yData = 
        	  { { y_1 },
        		{ y_2 },
        		{ y_3 },
        		{ y_4 },
        		{ y_5 },
        		{ y_6 },
        		{ y_7 },
        		{ y_8 },
        		{ y_9 },
        		{ y_10 },
        		{ y_11 },
        		{ y_12 },
        		{ y_13 },
        		{ y_14 },
        		{ y_15 },
        		{ y_16 },
        		{ y_17 },
        		{ y_18 },
        		{ y_19 },
        		{ y_20 },
        		{ y_21 },
        		{ y_22 },
        		{ y_23 },
        		{ y_24 },
        		{ y_25 } };
        
		data = new Data( xData, yData );
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//outputs the predicted first semester college GPA based on 
		//the ACT input
		if( e.getSource() == button)
		{
			int ACT;
			double GPA;
			String userInput = input.getText();
			Scanner scan = new Scanner( userInput );
			
			if(scan.hasNextInt()) 
			{
				ACT = scan.nextInt();
				GPA = data.getSlope() * ACT + data.getConstant();
				
				GPA *= 100;
				GPA = (int) GPA;
				GPA = GPA / 100;
				
				if( GPA > 4 )
				{
					output.setText( "4.00" );
				}
				else
				{
					output.setText("" + GPA);
				}
			}
			scan.close();
		}
	}	
}
