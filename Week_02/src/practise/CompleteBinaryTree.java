package practise;

import java.util.Arrays;

public class CompleteBinaryTree {

    public String[] nodes;

    public CompleteBinaryTree(int capacity) {
        nodes = new String[capacity + 1];
    }

    public static void main(String[] args) {
        int capacity = 10;
        CompleteBinaryTree tree = new CompleteBinaryTree(capacity);
        for (int i = 1; i <= capacity; i++) {
            tree.nodes[i] = new String(new char[] {(char) (i + 64)});
        }
        tree.print();
        tree.printByLevel(1);
        tree.printByLevel(2);
        tree.printByLevel(3);
        tree.printByLevel(4);
        System.out.println("E parent: " + tree.findParent("E"));
        System.out.println("F parent: " + tree.findParent("F"));
        System.out.println("H parent: " + tree.findParent("H"));
        System.out.println("p parent: " + tree.findParent("p"));
        System.out.println("A parent: " + tree.findParent("A"));

        System.out.println("A left child: " + tree.findLeftChild("A"));
        System.out.println("E left child: " + tree.findLeftChild("E"));
        System.out.println("F left child: " + tree.findLeftChild("F"));
        System.out.println("A left child: " + tree.findRightChild("A"));
        System.out.println("E left child: " + tree.findRightChild("E"));
        System.out.println("F left child: " + tree.findRightChild("F"));
    }

    public void print() {
        for (int i = 1; i < nodes.length; i++) {
            System.out.print(nodes[i] + " ");
        }
        System.out.println();
    }

    public void printByLevel(int level) {
        int num = (int) Math.pow(2, level - 1);
        for (int i = 0; i < num; i++) {
            if (i + num == nodes.length) {
                break;
            }
            System.out.print(nodes[i + num] + " ");
        }
        System.out.println();
    }

    public String findParent(String node) {
        int index = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (node.equals(nodes[i])) {
                index = i;
                break;
            }
        }
        if (index <= 1) {
            return null;
        }
        return nodes[index / 2];
    }

    public String findLeftChild(String node) {
        int index = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (node.equals(nodes[i])) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            return null;
        }
        return index * 2 >= nodes.length ? null : nodes[index * 2];
    }

    public String findRightChild(String node) {
        int index = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (node.equals(nodes[i])) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            return null;
        }
        return index * 2 + 1 >= nodes.length ? null : nodes[index * 2 + 1];
    }
}
