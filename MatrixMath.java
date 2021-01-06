import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MatrixMath {

    public void printMatrix(double[][] matrix){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_DOWN);

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public void addMatrices(double[][] matrixA, double[][] matrixB) {
        if(matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length){
            double[][] matrix = new double[matrixA.length][matrixA[0].length];

            for(int i = 0; i < matrixA.length; i++) {
                for(int j = 0; j < matrixA[0].length; j++) {
                    matrix[i][j] = matrixA[i][j] + matrixB[i][j];
                }
            }

            printMatrix(matrix);
        }else{
            System.out.println("The operation cannot be performed.\n");
        }
    }

    public void scalarMultiply(double c, double[][] matrixA) {
        double[][] matrix = new double[matrixA.length][matrixA[0].length];

        for(int i = 0; i < matrixA.length; i++) {
            for(int j = 0; j < matrixA[0].length; j++) {
                matrix[i][j] = c * matrixA[i][j];
            }
        }

        printMatrix(matrix);
    }

    public void multiplyMatrices(double[][] matrixA, double[][] matrixB) {
        if(matrixA[0].length == matrixB.length){
            double[][] matrix = new double[matrixA.length][matrixB[0].length];

            for(int i = 0; i < matrixA.length; i++) {
                for(int j = 0; j < matrixB[0].length; j++) {
                    for(int k = 0; k < matrixB.length; k++) {
                        matrix[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }

            printMatrix(matrix);
        }else {
            System.out.println("The operation cannot be performed.\n");
        }
    }

    public void transposeMatrix(double opt , double[][] matrixA) {
        double[][] matrix = new double[matrixA.length][matrixA[0].length];

        if(opt == 1) {
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixA.length; j++) {
                    matrix[i][j] = matrixA[j][i];
                }
            }
            printMatrix(matrix);
        }else if(opt == 2) {
            int a=0;
            int b=0;
            for (int i = matrixA.length - 1; i >= 0; i--) {
                for (int j = matrixA.length - 1; j >= 0; j--) {
                    matrix[a][b] = matrixA[j][i];
                    b++;
                }
                a++;
                b=0;
            }
            printMatrix(matrix);
        }else if(opt == 3) {
            int b=0;
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = matrixA.length - 1; j >= 0; j--) {
                    matrix[i][b] = matrixA[i][j];
                    b++;
                }
                b=0;
            }
            printMatrix(matrix);
        }else if(opt == 4) {
            int a=0;
            for (int i = matrixA.length - 1; i >= 0; i--) {
                for (int j = 0; j < matrixA.length; j++) {
                    matrix[a][j] = matrixA[i][j];
                }
                a++;
            }
            printMatrix(matrix);
        } else {
            System.out.println("The operation cannot be performed.\n");
        }
    }

    public double determinantOfMatrix(double[][] matrix, int size){
        double D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (size == 1)  return matrix[0][0];

        double sign = 1; // To store sign multiplier

        // Iterate for each element of first row
        for (int f = 0; f < size; f++)
        {
            // Getting Cofactor of A[0][f]
            double[][] temp = getCofactorMatrix(matrix, 0, f, size);
            D += sign * matrix[0][f] * determinantOfMatrix(temp, size - 1);

            // terms are to be added with alternate sign
            sign = -sign;
        }
        return D;
    }

    private double[][] getCofactorMatrix(double[][] matrix, int p, int q, int size) {
        int i = 0, j = 0;
        double[][] temp = new double[size][size];

        // Looping for each element of the matrix
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // Copying into temporary matrix only those element
                // which are not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == size - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return temp;
    }

    private double[][] getAdjointMatrix(double[][] matrix, int size){
        double[][] adjMatrix = new double[size][size];

        if(size == 1) {
            adjMatrix[0][0] = 1;
        }else{
            double sign;

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    double[][] temp = getCofactorMatrix(matrix, i, j, size);
                    sign = ((i + j) % 2 == 0)? 1: -1;
                    adjMatrix[j][i] = (sign)*(determinantOfMatrix(temp, size-1));

                }
            }
        }

        return adjMatrix;
    }

    public void inverseMatrix(double[][] matrix, int size) {
        double det = determinantOfMatrix(matrix, size);
        double[][] inverseMatrix = new double[size][size];

        if(det == 0){
            System.out.println("This matrix doesn't have an inverse");
        }else{
            // Find adjoint
            double[][] adj = getAdjointMatrix(matrix, size);

            // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    inverseMatrix[i][j] = adj[i][j]/(float)det;

            System.out.println("The result is:");
            printMatrix(inverseMatrix);
        }
    }

}
