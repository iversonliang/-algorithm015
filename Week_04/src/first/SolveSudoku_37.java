package first;

import java.util.*;

public class SolveSudoku_37 {

    public static void main(String[] args) {
        SolveSudoku_37 solveSudoku_37 = new SolveSudoku_37();
        char[][] board = getBorad();
        solveSudoku_37.solveSudoku2(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        int endPosition = 8;
        for (int i = 8; i >= 0; i--) {
            if (board[8][i] == '.') {
                endPosition = i + 1;
                break;
            }
        }
        dfs(board, 0, 0, endPosition);
    }

    public boolean dfs(char[][] board, int x, int y, int end) {
        if (x == 8 && y == end) {
            return true;
        }
        if (y == 9) {
            x++;
            y = 0;
        }

        int j = y;
        for (int i = x; i < 9; i++) {
            for (; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                List<Integer> valueList = getAvailable(board, i, j);
                for (int n = 0; n < valueList.size(); n++) {
                    board[i][j] = (char) (valueList.get(n) + '0');
                    if (!check(board, i, j)) {
                        board[i][j] = '.';
                        if (n == valueList.size() - 1) {
                            return false;
                        }
                        continue;
                    }
                    boolean result = dfs(board, i, j + 1, end);
                    if (result) {
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
            j = 0;
        }
        return false;
    }

    public List<Integer> getAvailable(char[][] board, int x, int y) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            list.add(i);
        }
        return list;
    }

    public boolean check(char[][] board, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (i != x && board[i][y] == board[x][y]) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (j != y && board[x][j] == board[x][y]) {
                return false;
            }
        }
        int xStart = x - x % 3;
        int yStart = y - y % 3;
        for (int i = 0; i < 3; i++) {
            for (int  j = 0; j < 3; j++) {
                if (xStart + i != x && yStart + 1 != y && board[xStart + i][yStart + j] == board[x][y]) {
                    return false;
                }
            }
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
