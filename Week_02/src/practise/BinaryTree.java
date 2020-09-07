package practise;

import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTree {
    Node root;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
//        Node d = tree.new Node("D");
//        Node e = tree.new Node("E");
//        Node b = tree.new Node("B", d, e);
//        Node f = tree.new Node("F");
//        Node g = tree.new Node("G");
//        Node c = tree.new Node("C", f, g);
//        Node a = tree.new Node("A", b, c);
//        tree.root = a;

        Node three = tree.new Node("3");
        Node two = tree.new Node("2");
        two.left = three;
        Node one = tree.new Node("1");
        one.right = two;
        tree.root = one;

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
        System.out.println();
        tree.levelOrder(tree.root);
        System.out.println();

        System.out.println("tree heigth: " + tree.height(tree.root));
        System.out.println("tree heigth2: " + tree.heigth2(tree.root));
        System.out.println("tree level: " + tree.level(tree.root));

        tree.inOrder(tree.root);
        System.out.println();
        tree.inOrderWithStack(tree.root);
    }

    // 前序遍历
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    // 迭代法中序遍历
    public void inOrderWithStack(Node node) {
        Node curr = node;
        Stack<Node> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.value + " ");
            curr = curr.right;
        }
    }

    // 后序遍历
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    // 按层遍历
    public void levelOrder(Node root) {
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            System.out.print(node.value + " ");
        }
    }

    // 递归树的高度
    public int height(Node node) {
        if (node.left == null && node.right == null) {
            return 0;
        }
        if (node.left == null) {
            return Math.max(0, height(node.right) + 1);
        }
        if (node.right == null) {
            return Math.max(height(node.left) + 1, 0);
        }
        return Math.max(height(node.left) + 1, height(node.right) + 1);
    }

    // 按层遍历树的高度
    public int heigth2(Node node) {
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        queue.offer(node);
        int currentLevelCount = 1;
        int nextLevelCount = 0;
        int level = 0;
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            if (temp.left != null) {
                queue.offer(temp.left);
                nextLevelCount++;
            }
            if (temp.right != null) {
                queue.offer(temp.right);
                nextLevelCount++;
            }
            if (--currentLevelCount == 0) {
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
                level++;
            }
        }
        return level - 1;
    }

    // 树的层数
    public int level(Node node) {
        return heigth2(node) + 1;
    }

    private class Node {
        String value;
        Node left;
        Node right;
        public Node(String value) {
            this.value = value;
        }

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
