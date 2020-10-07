package first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class FindWords_212 {

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0
                || board == null || board.length == 0 || board[0].length == 0) {
            return result;
        }

        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = word;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(result, board, i, j, root);
            }
        }
        return result;
    }

    public void dfs(List<String> result, char[][] board, int i, int j, TrieNode node) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == '.') {
            return;
        }

        node = node.children[board[i][j] - 'a'];
        if (node == null) {
            return;
        }

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        char c = board[i][j];
        board[i][j] = '.';
        for (int n = 0; n < dx.length; n++) {
            dfs(result, board, i + dy[n], j + dx[n], node);
        }
        board[i][j] = c;
    }

    public class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private String word = null;
    }

    public static void main(String[] args) {
        FindWords_212 findWords212 = new FindWords_212();
        String[] words = {"oath","pea","eat","rain"};
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        findWords212.findWords(board, words).forEach(s -> System.out.print(s + " "));
    }
}
