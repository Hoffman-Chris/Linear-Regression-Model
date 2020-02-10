import org.ejml.simple.SimpleMatrix;
import org.ejml.dense.row.linsol.qr.LinearSolverQrHouseCol_DDRM;
import org.ejml.data.*;

/**
 * This class will do the computation for the class Driver.
 * This will include matrix multiplication and solving 
 * the linear system.
 * 
 * @author Chris Hoffman
 */
public class Data
{
	private SimpleMatrix X;
	private SimpleMatrix Y;
	private DMatrixRMaj X_hat;
	
	/**
	 * constructor for object Data. Takes in
	 * two dimensional arrays of test data and
	 * creates two matrices
	 * 
	 * @param x
	 * 			the ACT score data or independent variable of the sample data
	 * @param y 
	 * 			the first semester college GPA or the dependent variable of the sample data
	 * 			
	 */
	public Data( double[][] x, double[][] y )
	{
		X = new SimpleMatrix( x );
		Y = new SimpleMatrix( y );
	}
	
	/**
	 * solves the linear system giving us X^
	 * this gives the equation of the line of best fit
	 */
	public void solveSystem()
	{
		
		double[][] z = new double[][]{ {0}, {0} };
		X_hat = new DMatrixRMaj( z );
		
		//multiply X transpose by Y transpose and X
		Y = X.transpose().mult(Y);
		X = X.transpose().mult(X);
		
		//make X transpose * X and X transpose * Y A and b respectively
		DMatrixRMaj A = X.getMatrix();
		DMatrixRMaj b = Y.getMatrix();
		
		//solves the equation A * X_hat = b
		LinearSolverQrHouseCol_DDRM solve = new LinearSolverQrHouseCol_DDRM();
		solve.setA( A );
		solve.solve( b, X_hat);	
	
	}
	
	/**
	 * returns the slope of the line of best fit
	 * 
	 * @return the slope of the line
	 */
	public double getSlope()
	{
		double slope = X_hat.get(1, 0);
		return slope;
	}
	
	/**
	 * returns the y intercept of the line of best fit
	 * 
	 * @return the y intercept of the line
	 */
	public double getConstant()
	{
		double constant = X_hat.get(0, 0);
		return constant;
	}
}









