package first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class SolveNQueens_51 {

    public static void main(String[] args) {
        SolveNQueens_51 solveNQueens51 = new SolveNQueens_51();
        solveNQueens51.solveNQueens(8).forEach(r -> {
            r.forEach(line -> System.out.println(line));
            System.out.println();
        });
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }
        dfs(result, grid, 0);
        return result;
    }

    public void dfs(List<List<String>> result, char[][] grid, int row) {
        if (row == grid.length) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                list.add(new String(grid[i]));
            }
            result.add(list);
            return;
        }

        for (int col = 0; col < grid.length; col++) {
            if (isValid(grid, row, col)) {
                grid[row][col] = 'Q';
                dfs(result, grid, row + 1);
                grid[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] chess, int row, int col) {
        //判断当前列有没有皇后,因为他是一行一行往下走的，
        //我们只需要检查走过的行数即可，通俗一点就是判断当前
        //坐标位置的上面有没有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
