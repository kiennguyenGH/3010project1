import java.util.Scanner;
import java.lang.Math;

public class GaussianElimination
{

    public static double findLargest(double[] row)
    {
        double largest = 0;
        for (int i = 0; i < row.length-1; i++)
        {
            if (Math.abs(row[i]) > largest)
            {
                largest = Math.abs(row[i]);
            }
        }
        return largest;
    }

    public static double[] getColumn(double[][] matrix, int column)
    {
        double[] theColumn = new double[matrix.length];
        for (int i = 0; i < theColumn.length; i++)
        {
            theColumn[i] = matrix[i][column];
        }
        return theColumn;
    }

    public static double[][] swapRows(double[][] matrix, int row1, int row2)
    {
        double[][] theMatrix = matrix;
        double[] temp = matrix[row1];
        theMatrix[row1] = theMatrix[row2];
        theMatrix[row2] = temp;

        return theMatrix;
    }

    public static double[] divideArray(double[] row, double[] scale)
    {
        double[] array = new double[row.length];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = row[i]/scale[i];
        }
        return array;
    }

    public static void printMatrix(double[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int k = 0; k < matrix[i].length; k++)
            {
                System.out.print(matrix[i][k] + " ");
            }
        }
        System.out.println();
    }

    public static void printScales(double[] vector)
    {
        for (int i = 0; i < vector.length; i++)
        {
            System.out.print(vector[i] + " ");
        }
        System.out.println();
    }

    public static void computeGaussian(double[][] matrix)
    {
        int pivotRow = 0;
        double divideValue = 0;
        int[] indices = new int[matrix[0].length-1];
        double[] multipliers = new double[matrix.length];
        double[] scales;
        double[] largestValues = new double[matrix.length];
        double[][] theMatrix = matrix;
        for (int i = 0; i < largestValues.length; i++)
        {
            largestValues[i] = findLargest(matrix[i]);
        }
        for (int i = 0; i < matrix[0].length-1; i++)
        {

        }
    }

    public static double[][] getMatrix()
    {
        Scanner scan = new Scanner(System.in);
        int matrixSize = 0;
        while (matrixSize < 1 || matrixSize > 10)
        {
            System.out.println("How many rows is the matrix? (max 10)");
            matrixSize = scan.nextInt();
            scan.nextLine();
            if (matrixSize < 1 || matrixSize > 10)
            {
                System.out.println("Invalid input");
            }
            
        }
        double[][] rows = new double[matrixSize][matrixSize + 1];
        for (int i = 0; i < rows.length; i++)
        {
            String rowInput = "";
            System.out.println("Enter the coefficients for row " + (i + 1) + " (separated by spaces)");
            rowInput = scan.nextLine();
            String[] splitTest = rowInput.split("\\s+");
            while (splitTest.length != matrixSize + 1)
            {
                System.out.println("Invalid input");
                System.out.println("Enter the coefficients for row " + (i + 1) + " (separated by spaces)");
                rowInput = scan.nextLine();
                splitTest = rowInput.split("\\s+");
            }
            
            for (int k = 0; k < splitTest.length; k++)
            {
                rows[i][k] = Double.parseDouble(splitTest[k]);
            }
        }

        scan.close();
        return rows;
    }


    public static void main(String[] args)
    {
       Scanner input = new Scanner(System.in);
       int theInput = 0;
       while (theInput != 1 && theInput != 2)
       {
            System.out.println("Enter:\n1. To input the equations\n2. To read from a file");
            theInput = input.nextInt();
            input.nextLine();
            if (theInput != 1 && theInput != 2)
            {
                System.out.println("Invalid input");
            }
       }
       
       
       //Get user matrix
       if (theInput == 1)
       {
            double[][] test = getMatrix();
            computeGaussian(test);
            
            
        //Read file
       }
       else
       {

       }
       input.close();
    }
}