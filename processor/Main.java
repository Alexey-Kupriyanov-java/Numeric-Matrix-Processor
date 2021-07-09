package processor;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            int answer = showMainMenu();
            double[][] result;

            if (answer == 0) {
                break;
            }

            switch (answer) {
                case 1:
                    double[][] a = input(" first ");
                    double[][] b = input(" second ");
                    result = additional(a, b);
                    printResult(result, "The operation cannot be performed.");
                    break;
                case 2:
                    a = input(" ");
                    System.out.print("Enter constant: ");
                    double constant = scanner.nextDouble();
                    result = multipleByConstant(a, constant);
                    printResult(result, "The operation cannot be performed.");
                    break;
                case 3:
                    a = input(" first ");
                    b = input(" second ");
                    result = multiple(a, b);
                    printResult(result, "The operation cannot be performed.");
                    break;
                case 4:
                    answer = showTransposeMenu();
                    a = input(" ");
                    result = transpose(a, answer);
                    printResult(result, "The operation cannot be performed.");
                    break;
                case 5:
                    a = input(" ");
                    System.out.println(determinantOf(a));
                    break;
                case 6:
                    a = input(" ");
                    result = inverse(a);

                    printResult(result, "This matrix doesn't have an inverse.");
                    break;
            }
        }
    }

    private static void printResult(double[][] result, String errMessage) {
        if (result == null) {
            System.out.println(errMessage);
        } else {
            System.out.println("The result is:");
            print(result);
            System.out.println();
        }
    }

    private static int showMainMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    private static int showTransposeMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    private static double[][] input(String str) {
        System.out.printf("Enter size of%smatrix: ", str);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.printf("Enter%smatrix:%n", str);
        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }

    private static double[][] additional(double[][] a, double[][] b) {
        double[][] result;

        if (a.length != b.length || a[0].length != b[0].length) {
            return null;
        }

        result = new double[a.length][a[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    private static double[][] multipleByConstant(double[][] matrix, double constant) {
        double[][] result = matrix.clone();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = result[i][j] * constant;
            }
        }

        return result;
    }

    private static double[][] multiple(double[][] a, double[][] b) {
        double[][] result;

        if (a[0].length != b.length) {
            return null;
        }

        result = new double[a.length][b[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    private static double[][] transpose(double[][] a, int method) {
        double[][] result = new double[a.length][a[0].length];

        switch (method) {
            case 1:
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        result[i][j] = a[j][i];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        result[i][j] = a[result.length - 1 - j][result.length - 1 - i];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        result[i][j] = a[i][result.length - 1 - j];
                    }
                }
                break;
            case 4:
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        result[i][j] = a[result.length - 1 - i][j];
                    }
                }
                break;
        }

        return result;
    }

    private static double determinantOf(double[][] matrix) {
        double determinate = 0;

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        for (int k = 0; k < matrix.length; k++) {
            determinate += matrix[0][k] * cofactorOf(matrix, 0, k);
        }
        return determinate;
    }

    private static double cofactorOf(double[][] matrix, int row, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < minor.length; i++) {
            for (int j = 0; j < minor.length; j++) {
                int k = i;
                int l = j;
                if (i >= row) {
                    k++;
                }
                if (j >= col) {
                    l++;
                }
                minor[i][j] = matrix[k][l];
            }
        }
        return Math.pow(-1, row + col + 2) * determinantOf(minor);
    }

    private static double[][] inverse(double[][] matrix) {
        double[][] result;
        double determinant;
        double[][] cofactors = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                cofactors[i][j] = cofactorOf(matrix, i, j);
            }
        }

        determinant = determinantOf(matrix);
        result = determinant == 0 ? null :multipleByConstant(transpose(cofactors, 1), 1 / determinant);

        return result;
    }

    private static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}