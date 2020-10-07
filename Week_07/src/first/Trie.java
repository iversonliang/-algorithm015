package first;

public class Trie {

    private TrieNode root = new TrieNode('/');

    public Trie() {
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode(word.charAt(i));
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = findLastCharNode(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return findLastCharNode(prefix) != null;
    }

    private TrieNode findLastCharNode(String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            TrieNode child = node.children[str.charAt(i) - 'a'];
            if (child == null) {
                return null;
            }
            node = child;
        }
        return node;
    }

    public class TrieNode {
        private char data;
        private boolean isEnd = false;
        private TrieNode[] children = new TrieNode[26];

        public TrieNode(char c) {
            this.data = c;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));       // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }
}
