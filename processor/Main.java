package processor;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] a = input();
        int constant = scanner.nextInt();

        print(multipleByConstant(a, constant));

//        int[][] b = input();
//
//        if(a.length != b.length || a[0].length != b[0].length) {
//            System.out.println("ERROR");
//        } else {
//            print(additional(a, b));
    }

    private static int[][] multipleByConstant(int[][] matrix, int constant) {
        int[][] result = matrix.clone();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = result[i][j] * constant;
            }
        }

        return result;
    }

    private static int[][] additional(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    private static int[][] input() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        return matrix;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}