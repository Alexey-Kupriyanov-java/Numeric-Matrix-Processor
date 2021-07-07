package processor;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n1 = scanner.nextInt();
        int m1 = scanner.nextInt();
        int[][] a = fillMatrix(n1, m1);

        int n2 = scanner.nextInt();
        int m2 = scanner.nextInt();
        int[][] b = fillMatrix(n2, m2);

        if(n1 != n2 || m1 != m2) {
            System.out.println("ERROR");
        } else {
            printMatrix(additional(a, b));
        }
    }

    public static int[][] additional(int[][] a, int[][] b) {
        int[][] matrix = new int[a.length][a[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = a[i][j] + b[i][j];
            }
        }
        return matrix;
    }

    public static int[][] fillMatrix(int n, int m) {
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
