package first;

public class MinPathSum_64 {

    public static void main(String[] args) {
//        int[][] grid = new int[3][3];
//        grid[0] = new int[] {1, 3, 1};
//        grid[1] = new int[] {1,5,1};
//        grid[2] = new int[] {4,2,1};
        int[][] grid = {{0,1}, {1,0}};
        MinPathSum_64 minPathSum64 = new MinPathSum_64();
        System.out.println(minPathSum64.minPathSum2(grid));
    }

    public int minPathSum(int[][] grid) {
        // 1. 重复子问题  dp[i][j] = Min(dp[i+1][j], dp[i][j+1]) + num[i][j]
        // 2. 状态数组    dp[i][j]
        // 3. dp方程

        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (j == dp[0].length - 1 && i != dp.length - 1) {
                    dp[i][j] = dp[i+1][j] + grid[i][j];
                } else if (i == dp.length - 1 && j != dp[0].length - 1) {
                    dp[i][j] = dp[i][j+1] + grid[i][j];
                } else if (i != dp.length - 1 && j != dp[0].length - 1) {
                    dp[i][j] = Math.min(dp[i][j+1], dp[i+1][j]) + grid[i][j];
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }

        return dp[0][0];
    }

    public int minPathSum2(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j != grid[0].length - 1) {
                    dp[j] = dp[j+1] + grid[i][j];
                } else if (j == grid[0].length - 1 && i != grid.length - 1) {
                    dp[j] += grid[i][j];
                } else if (i != grid.length - 1 && j != grid[0].length - 1) {
                    dp[j] = dp[j] < dp[j+1] ? dp[j] + grid[i][j] : dp[j+1] + grid[i][j];
                } else {
                    dp[j] = grid[i][j];
                }
            }
        }
        return dp[0];
    }
}
