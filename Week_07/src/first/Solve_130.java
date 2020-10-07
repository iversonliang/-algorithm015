package first;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solve_130 {

    public void solve2(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) && board[i][j] == 'O') {
                    bfs(board, i, j);
                }
            }
        }

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    public void bfs(char[][] board, int i, int j) {
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        Queue<Integer> queue = new ArrayDeque<>();
        board[i][j] = '#';
        queue.offer(i);
        queue.offer(j);

        while (!queue.isEmpty()) {
            int tempY = queue.poll();
            int tempX = queue.poll();

            for (int n = 0; n < dx.length; n++) {
                int y = tempY + dy[n];
                int x = tempX + dx[n];

                if (y < 0 || y >= board.length || x < 0 || x >= board[0].length || board[y][x] != 'O') {
                    continue;
                }
                board[y][x] = '#';
                queue.offer(y);
                queue.offer(x);
            }
        }
    }

    public static void main(String[] args) {
        Solve_130 solve130 = new Solve_130();
//        char[][] board = {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}
//        };
        char[][] board = {
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}
        };
//        char[][] board = {
//                {'O','O','O'},
//                {'O','O','O'},
//                {'O','O','O'}
//        };
        solve130.solve(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        UnionFind unionFind = new UnionFind(row * col + 1);
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, -1, 0, 1};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    unionFind.union(row * col, i * col + j);
                    continue;
                }
                for (int n = 0; n < dx.length; n++) {
                    int x = j + dx[n];
                    int y = i + dy[n];

                    if (board[y][x] == 'O') {
                        unionFind.union(y * col + x, i * col + j);
                    }
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O' && !unionFind.isConnected(i * col + j, row * col)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public class UnionFind {
        private int[] parent;
        public UnionFind(int count) {
            parent = new int[count];
            for (int i = 0; i < count; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            if (p == parent[p]) {
                return p;
            }
            return parent[p] = find(parent[p]);
        }

        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }
}
