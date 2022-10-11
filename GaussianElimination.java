import java.util.Scanner;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;

// Kien Nguyen
// CS 3010.03-1


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

    public static double[] multiplyArray(double[] array, double multiplier)
    {
        double[] multArray = new double[array.length];
        for (int i = 0; i < array.length; i++)
        {
            multArray[i] = array[i] * multiplier;
        }
        return multArray;
    }

    public static double[] removeFirstIndex(double[] array)
    {
        double[] theArray = new double[array.length-1];
        for (int i = 0; i < theArray.length; i++)
        {
            theArray[i] = array[i + 1];
        }
        return theArray;
    }

    public static int findLargestIndex(double[] row)
    {
        int largest = 0;
        for (int i = 0; i < row.length; i++)
        {
            if (row[i] > row[largest])
            {
                largest = i;
            }
        }
        return largest;
    }

    public static double[] getColumn(double[][] matrix, int column)
    {
        double[] theColumn = new double[matrix.length-column];
        for (int i = 0; i < theColumn.length; i++)
        {
            theColumn[i] = matrix[i + column][column];
        }
        return theColumn;
    }

    public static double[] prepMultiplier(double[][] matrix, int column)
    {
        double[] theColumn = new double[matrix.length-(column+1)];
        for (int i = 0; i < theColumn.length; i++)
        {
            theColumn[i] = matrix[i + column + 1][column];
        }
        return theColumn;
    }


    public static double[] swapArray(double[] array, int index1, int index2)
    {
        double[] theArray = array;
        double temp = array[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
        return theArray;
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

    public static double[] divideArrayAbs(double[] row, double[] scale)
    {
        double[] array = new double[row.length];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = Math.abs(row[i]/scale[i]);
        }
        return array;
    }

    public static double[] divideArray(double[] row, double num)
    {
        double[] array = new double[row.length];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = row[i]/num;
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
            System.out.println();
        }
        
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
        double[] multipliers = new double[matrix.length];
        double[] scales;
        double[] largestValues = new double[matrix.length];
        double[][] theMatrix = matrix;
        for (int i = 0; i < largestValues.length; i++)
        {
            largestValues[i] = findLargest(matrix[i]);
        }
        for (int i = 0; i < matrix.length-1; i++)
        {
            System.out.println("Current matrix: ");
            printMatrix(theMatrix);
            System.out.println();
            scales = getColumn(theMatrix, i);
            //Get Ratios
            scales = divideArrayAbs(scales, largestValues);
            System.out.println("Scaled ratios: ");
            printScales(scales);
            System.out.println();
            pivotRow = findLargestIndex(scales);
            System.out.println("Pivot row:\n" + (pivotRow + i + 1) + "\n");

            //Move pivot row to top
            scales = swapArray(scales, 0, pivotRow);
            theMatrix = swapRows(theMatrix, i, pivotRow + i);
            largestValues = swapArray(largestValues, 0, pivotRow);
            largestValues = removeFirstIndex(largestValues);
            //Elimination
            multipliers = divideArray(prepMultiplier(theMatrix, i), theMatrix[i][i]);
            System.out.println("Multipliers: ");
            printScales(multipliers);
            System.out.println();


            System.out.println("Before elimination: ");
            printMatrix(theMatrix);
            System.out.println();

            for (int k = 1; k < multipliers.length + 1; k++)
            {
                for (int j = i; j < theMatrix[0].length; j++)
                {
                    theMatrix[k + i][j] = theMatrix[k + i][j] - (multipliers[k-1] * theMatrix[i][j]);
                }
            }
            System.out.println("After elimination: ");
            printMatrix(theMatrix);
            System.out.println();

        }

        //Back Substitution
        double[] answers = new double[theMatrix.length];
        // answers[0] = theMatrix[theMatrix.length-1][theMatrix[0].length-1]/theMatrix[theMatrix.length-1][theMatrix[0].length-2];
        // System.out.println(answers[0]);
        int count = 0;
        for (int i = theMatrix.length-1; i >= 0; i--)
        {
            for (int j = 0; j < count; j++)
            {
                // System.out.println(theMatrix[i][theMatrix[i].length-1] + " -= " + "(" + answers[j] + " * " + theMatrix[i][theMatrix[0].length-j-2] + ")");
                theMatrix[i][theMatrix[i].length-1] -= (answers[j] * theMatrix[i][theMatrix[i].length-j-2]);
                // printMatrix(theMatrix);
                // System.out.println();
                // theMatrix[i][theMatrix[0].length-1] -= (answers[j] * theMatrix[i][theMatrix[0].length-count-1]);
            }
            answers[count] = theMatrix[i][theMatrix[0].length-1]/theMatrix[i][i];
            // System.out.println("Answer " + answers[count]);
            count++;
        }
        count = 1;
        for (int i = answers.length -1; i >= 0; i--)
        {
            System.out.println("x" + count + ": " + answers[i]);
            count++;
        }
        // printMatrix(theMatrix);
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
            System.out.println();
            
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
       System.out.println();
       
       //Get user matrix
       if (theInput == 1)
       {
            double[][] test = getMatrix();
            computeGaussian(test);
            
            
        
       }
       //Read file
       else
       {
            System.out.println("Enter the name of the file: ");
            String fileName = input.nextLine();
            double[][] test;
            try {
                File file = new File(fileName);
                Scanner fileReader = new Scanner(file);
                String[] array = fileReader.nextLine().split("\\s+");
                test = new double[array.length-1][array.length];
                int count = 0;
                for (int i = 0; i < array.length; i++)
                {
                    test[count][i] = Double.parseDouble(array[i]);
                }
                count++;
                while(fileReader.hasNextLine())
                {
                    array = fileReader.nextLine().split("\\s+");
                    for (int i = 0; i < array.length; i++)
                    {
                        test[count][i] = Double.parseDouble(array[i]);
                    }
                    count++;
                }
                computeGaussian(test);
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
            

       }
       input.close();
    }
}