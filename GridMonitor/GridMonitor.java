import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that creates and analyzes a 2D array
 * 
 * @author Gavin Wale
 *
 */
public class GridMonitor implements GridMonitorInterface {

	private int row;
	private int col;
	private double[][] baseGrid;
	private double[][] sumGrid;
	private double[][] avgGrid;
	private double[][] deltaGrid;
	private boolean[][] dangerGrid;

	/**
	 * Performs computations on a 2D array
	 * 
	 * @param filename - String that represents a file
	 */
	public GridMonitor(String filename) {

		try {
			readFile(filename);
		} catch (FileNotFoundException e) {
		}

		// SumGrid math
		double[][] nonMutableCopy = new double[row][col]; // Double[][] that does not change as for loop is executed
		sumGrid = copyGrid(baseGrid);
		nonMutableCopy = copyGrid(baseGrid);
		double a;
		double b;
		double c;
		double d;
		double sum;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				// i value sums
				if (i - 1 >= 0) { // If there is a value above the square
					a = nonMutableCopy[i - 1][j]; // Set a to that value
				} else {
					a = nonMutableCopy[i][j];
				}

				if (i + 1 <= row - 1) { // If there is a value above the square
					b = nonMutableCopy[i + 1][j]; // Set b to that value
				} else {
					b = nonMutableCopy[i][j];
				}

				// j value sums
				if (j - 1 >= 0) { // If there is a value left of the square
					c = nonMutableCopy[i][j - 1]; // Set c to that value
				} else {
					c = nonMutableCopy[i][j];
				}

				if (j + 1 <= col - 1) { // If there is a value right of the square
					d = nonMutableCopy[i][j + 1]; // Set d to that value
				} else {
					d = nonMutableCopy[i][j];
				}

				sum = a + b + c + d; // Sum a,b,c,d

				sumGrid[i][j] = sum; // Set each iteration of sumGrid to the value of sum

			}
		}

		// AverageGrid math

		avgGrid = copyGrid(sumGrid);
		nonMutableCopy = copyGrid(sumGrid);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				avgGrid[i][j] = avgGrid[i][j] / 4;

			}
		}

		// DeltaGrid math

		deltaGrid = copyGrid(avgGrid);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				deltaGrid[i][j] = deltaGrid[i][j] / 2;

			}
		}

		// DangerGrid logic

		dangerGrid = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				dangerGrid[i][j] = Math.abs(baseGrid[i][j]) > Math.abs(deltaGrid[i][j]) + Math.abs(avgGrid[i][j])
						|| Math.abs(baseGrid[i][j]) < Math.abs(deltaGrid[i][j]);

			}
		}

	}

	/**
	 * Scans a file and creates initial baseGrid
	 * 
	 * @param filename - String that represents a file
	 * @throws FileNotFoundException
	 */
	private void readFile(String filename) throws FileNotFoundException {

		File file = new File(filename);

		// try {
		Scanner scan = new Scanner(file); // Opens scanner and new file

		this.row = scan.nextInt();
		this.col = scan.nextInt();

		baseGrid = new double[row][col]; // = copyGrid();

		scan.close();

		Scanner rowScan = new Scanner(file);

		rowScan.nextLine();
		for (int i = 0; i < row; i++) {
			Scanner colScan = new Scanner(rowScan.nextLine());
			for (int j = 0; j < col; j++) {
				baseGrid[i][j] = colScan.nextDouble();
			}
			colScan.close();
		}
		rowScan.close();

	}

	/**
	 * Returns the initial grid read from input file
	 * 
	 * @return baseGrid - a double[][]
	 */
	public double[][] getBaseGrid() {

		return baseGrid;

	}

	/**
	 * Returns sumGrid representing sum of adjacent elements
	 * 
	 * @return sumGrid - a double[][]
	 */
	public double[][] getSurroundingSumGrid() {

		return sumGrid;

	}

	/**
	 * Returns avgGrid representing average of adjacent elements
	 * 
	 * @return avgGrid - a double[][]
	 */
	public double[][] getSurroundingAvgGrid() {

		return avgGrid;

	}

	/**
	 * Returns deltaGrid, an array where each element represents the maximum delta
	 * from the average
	 * 
	 * @return deltaGrid - a double[][]
	 */
	public double[][] getDeltaGrid() {

		return deltaGrid;
	}

	/**
	 * Returns dangerGrid, a boolean array, where each element represents whether an
	 * element is in danger
	 * 
	 * @return dangerGrid - a double[][]
	 */
	public boolean[][] getDangerGrid() {

		return dangerGrid;
	}

	/**
	 * Returns a copy of the given grid
	 * 
	 * @param grid - a double[][]
	 * @return deltaGrid - a double[][]
	 */
	public double[][] copyGrid(double[][] grid) {

		double[][] newGrid = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				newGrid[i][j] = grid[i][j];

			}
		}

		return newGrid;

	}

	/**
	 * Returns a string representing baseGrid and dangerGrid
	 * 
	 * @return baseString + dangerString - strings representing two different double[][]
	 */
	public String toString() {

		String baseString = null;
		String dangerString = null;

		for (int i = 0; i < baseGrid.length; i++) {
			for (int j = 0; j < baseGrid[i].length; j++) {
				baseString += " " + baseGrid[i][j]; // Adds numbers to printed array
			}
			baseString += "\n"; // Prints new line
		}

		for (int i = 0; i < dangerGrid.length; i++) {
			for (int j = 0; j < dangerGrid[i].length; j++) {
				dangerString += " " + dangerGrid[i][j]; // Adds numbers to printed array
			}
			dangerString += "\n"; // Prints new line
		}

		return baseString + "\n\n" + dangerString;

	}

}
