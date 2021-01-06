import java.util.Scanner;

public class InputHandler {

    private final Scanner input = new Scanner(System.in);
    private final MatrixMath matrixMath = new MatrixMath();

    public void startMessage() {
        String options = "1. Add matrices\n" + "2. Multiply matrix by a constant\n" + "3. Multiply matrices\n"
                + "4. Transpose matrix\n" + "5. Calculate a determinant\n" + "6. Inverse matrix\n" + "0. Exit";
        String transposeOpt = "1. Main diagonal\n" + "2. Side diagonal\n" + "3. Vertical line\n" + "4. Horizontal line";

        System.out.println(options + "\nYour choice: ");

        while(input.hasNext()) {
            int userInput = input.nextInt();
            double[][] matrixA;
            double[][] matrixB;
            double scalar;

            switch (userInput) {
                case 0: break;
                case 1:
                    matrixA = collectInput("first");
                    matrixB = collectInput("second");
                    matrixMath.addMatrices(matrixA, matrixB);
                    break;
                case 2:
                    matrixA = collectInput("".trim());
                    System.out.println("Enter constants: ");
                    scalar = input.nextDouble();
                    matrixMath.scalarMultiply(scalar,matrixA);
                    break;
                case 3:
                    matrixA = collectInput("first");
                    matrixB = collectInput("second");
                    matrixMath.multiplyMatrices(matrixA, matrixB);
                    break;
                case 4:
                    System.out.println("\n" + transposeOpt);
                    scalar = input.nextDouble();
                    matrixA = collectInput("");
                    matrixMath.transposeMatrix(scalar, matrixA);
                    break;
                case 5:
                    matrixA = collectInput("");
                    System.out.println("The result is: \n" + matrixMath.determinantOfMatrix(matrixA, matrixA.length));
                    break;
                case 6:
                    matrixA = collectInput("");
                    matrixMath.inverseMatrix(matrixA, matrixA.length);
                    break;
            }

            System.out.println("\n" + options + "Your choice: \n");
        }
    }

    private double[][] collectInput(String numMatrix){
        System.out.println("Enter size of " + numMatrix.trim() + " matrix: ");
        int row = input.nextInt();
        int col = input.nextInt();

        double[][] matrix = new double[row][col];

        System.out.println("Enter " + numMatrix + " matrix: ");

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }
        return matrix;
    }
}
