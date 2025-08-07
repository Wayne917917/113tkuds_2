public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };

        System.out.println("矩陣 A + B：");
        printMatrix(addMatrix(A, B));

        System.out.println("矩陣 A × B：");
        printMatrix(multiplyMatrix(A, B));

        System.out.println("矩陣 A 的轉置：");
        printMatrix(transposeMatrix(A));

        System.out.println("矩陣 A 的最大值與最小值：");
        findMaxMin(A);
    }

    static int[][] addMatrix(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                for (int k = 0; k < A[0].length; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }

    static int[][] transposeMatrix(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[j][i] = A[i][j];
        return result;
    }

    static void findMaxMin(int[][] matrix) {
        int max = matrix[0][0], min = matrix[0][0];
        for (int[] row : matrix)
            for (int value : row) {
                if (value > max) max = value;
                if (value < min) min = value;
            }
        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) System.out.print(val + "\t");
            System.out.println();
        }
    }
}
