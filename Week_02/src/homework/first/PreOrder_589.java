package homework.first;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrder_589 {

    public static void main(String[] args) {
        Node node5 = new Node(5, new ArrayList<>());
        Node node6 = new Node(6, new ArrayList<>());
        List<Node> childList3 = new ArrayList<>();
        childList3.add(node5);
        childList3.add(node6);
        Node node3 = new Node(3, childList3);

        Node node2 = new Node(2, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        List<Node> childList1 = new ArrayList<>();
        childList1.add(node3);
        childList1.add(node2);
        childList1.add(node4);
        Node node1 = new Node(1, childList1);

        PreOrder_589 preOrder_589 = new PreOrder_589();
        preOrder_589.preorder2(node1).forEach(num -> System.out.print(num + " "));
    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        doPreOrder(root, list);
        return list;
    }

    public void doPreOrder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        for (Node child : node.children) {
            doPreOrder(child, list);
        }
    }

    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        Node temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            list.add(temp.val);
            if (temp.children.size() > 0) {
                for (int i = temp.children.size() - 1; i >= 0; i--) {
                    stack.push(temp.children.get(i));
                }
            }
        }
        return list;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
