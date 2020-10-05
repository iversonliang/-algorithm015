package first;

public class MaximalSquare_221 {

    public static void main(String[] args) {
        MaximalSquare_221 maximalSquare221 = new MaximalSquare_221();
//        char[][] matrix = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };

        char[][] matrix = {{'1'}};

        System.out.println(maximalSquare221.maximalSquare(matrix));
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxSide = 0;

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    if (i < matrix.length - 1 && j < matrix[0].length - 1) {
                        dp[i][j] = Math.min(Math.min(dp[i][j+1], dp[i+1][j]), dp[i+1][j+1]) + 1;
                    } else if (i == matrix.length - 1 || j == matrix[0].length - 1) {
                        dp[i][j] = matrix[i][j] - '0';
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
