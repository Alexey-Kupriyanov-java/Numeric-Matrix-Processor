package processor;

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
                    printResult(result);
                    break;
                case 2:
                    a = input(" ");
                    System.out.print("Enter constant: ");
                    double constant = scanner.nextDouble();
                    result = multipleByConstant(a, constant);
                    printResult(result);
                    break;
                case 3:
                    a = input(" first ");
                    b = input(" second ");
                    result = multiple(a, b);
                    printResult(result);
                    break;
                case 4:
                    answer = showTransposeMenu();
                    a = input(" ");
                    result = transpose(a, answer);
                    printResult(result);
                    break;
                case 5:
                    a = input(" ");
                    System.out.println(determinateOf(a));
                    break;
            }
        }
    }

    private static void printResult(double[][] result) {
        if (result == null) {
            System.out.println("The operation cannot be performed.");
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

    private static double[][] transpose(double[][] a, int answer) {
        double[][] result = new double[a.length][a[0].length];

        switch (answer) {
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

    private static double determinateOf(double[][] a) {
        double determinate = 0;

        if (a.length == 2) {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        }
        for (int k = 0; k < a.length; k++) {
            double[][] minor = new double[a.length - 1][a.length - 1];
            for (int i = 0; i < minor.length; i++) {
                for (int j = 0; j < minor.length; j++) {
                    if (j >= k) {
                        minor[i][j] = a[i + 1][j + 1];
                    } else {
                        minor[i][j] = a[i + 1][j];
                    }
                }
            }
            determinate += Math.pow(-1, k + 2) * a[0][k] * determinateOf(minor);
        }
        return determinate;
    }

    private static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}