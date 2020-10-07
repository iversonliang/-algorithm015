package second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveSudoku_37 {

    public static void main(String[] args) {
        SolveSudoku_37 solveSudoku_37 = new SolveSudoku_37();
        char[][] board = getBorad();
        solveSudoku_37.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        dfsFill(board, 0, 0);
    }

    public boolean dfsFill(char[][] board, int i, int j) {
        if (i == board.length) {
            return true;
        }
        if (j == board[0].length) {
            return dfsFill(board, i + 1, 0);
        }
        if (board[i][j] != '.') {
            return dfsFill(board, i, j + 1);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, i, j, c)) {
                board[i][j] = c;
                if (dfsFill(board, i, j+1)) {
                    return true;
                }
            }
        }
        board[i][j] = '.';
        return false;
    }

    // 判断 board[i][j] 是否可以填入 n
    boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }















    public void solveSudoku2(char[][] board) {
        dfs(board,0);
    }

    private boolean dfs(char[][] board, int d) {
        if (d==81) return true; //found solution
        int i=d/9, j=d%9;
        if (board[i][j]!='.') return dfs(board,d+1);//prefill number skip

        boolean[] flag=new boolean[10];
        validate(board,i,j,flag);
        for (int k=1; k<=9; k++) {
            if (flag[k]) {
                board[i][j]=(char)('0'+k);
                if (dfs(board,d+1)) return true;
            }
        }
        board[i][j]='.'; //if can not solve, in the wrong path, change back to '.' and out
        return false;
    }

    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag,true);
        for (int k=0; k<9; k++) {
            if (board[i][k]!='.') flag[board[i][k]-'0']=false;
            if (board[k][j]!='.') flag[board[k][j]-'0']=false;
            int r=i/3*3+k/3;
            int c=j/3*3+k%3;
            if (board[r][c]!='.') flag[board[r][c]-'0']=false;
        }
    }



    public static char[][] getBorad() {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = '.';
            }
        }
        board[0][0] = '5';
        board[0][1] = '3';
        board[0][4] = '7';

        board[1][0] = '6';
        board[1][3] = '1';
        board[1][4] = '9';
        board[1][5] = '5';

        board[2][1] = '9';
        board[2][2] = '8';
        board[2][7] = '6';

        board[3][0] = '8';
        board[3][4] = '6';
        board[3][8] = '3';

        board[4][0] = '4';
        board[4][3] = '8';
        board[4][5] = '3';
        board[4][8] = '1';

        board[5][0] = '7';
        board[5][4] = '2';
        board[5][8] = '6';

        board[6][1] = '6';
        board[6][6] = '2';
        board[6][7] = '8';

        board[7][3] = '4';
        board[7][4] = '1';
        board[7][5] = '9';
        board[7][8] = '5';

        board[8][4] = '8';
        board[8][7] = '7';
        board[8][8] = '9';
        return board;
    }

}
