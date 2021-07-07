package processor;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int answer = scanner.nextInt();
            double[][] result = null;

            if (answer == 0) {
                break;
            }

            switch (answer) {
                case 1:
                    double[][] a = input(" first ");
                    double[][] b = input(" second ");
                    result = additional(a, b);
                    break;
                case 2:
                    a = input(" ");
                    System.out.print("Enter constant: ");
                    double constant = scanner.nextDouble();
                    result = multipleByConstant(a, constant);
                    break;
                case 3:
                    a = input(" first ");
                    b = input(" second ");
                    result = multiple(a, b);
                    break;
            }
            if (result == null) {
                System.out.println("The operation cannot be performed.");
            } else {
                System.out.println("The result is:");
                print(result);
                System.out.println();
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
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

    private static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}