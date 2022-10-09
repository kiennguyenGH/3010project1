import java.util.Scanner;

public class GaussianElimination
{

    public void computeGaussian()
    {

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
       
       
       //Get user rows
       if (theInput == 1)
       {
            double[][] test = getMatrix();
            for (int i = 0; i < test.length; i++)
            {
                for (int k = 0; k < test[i].length; k++)
                {
                    System.out.print(test[i][k] + " ");
                }
                System.out.println();
            }
            
            
        //Read file
       }
       else
       {

       }
       input.close();
    }
}