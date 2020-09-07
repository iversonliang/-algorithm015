package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree {

    public Node root;

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(new Node(33), tree.root);
        tree.insert(new Node(16), tree.root);
        tree.insert(new Node(50), tree.root);
        tree.insert(new Node(13), tree.root);
        tree.insert(new Node(18), tree.root);
        tree.insert(new Node(34), tree.root);
        tree.insert(new Node(58), tree.root);
        tree.insert(new Node(15), tree.root);
        tree.insert(new Node(17), tree.root);
        tree.insert(new Node(25), tree.root);
        tree.insert(new Node(51), tree.root);
        tree.insert(new Node(66), tree.root);
        tree.insert(new Node(19), tree.root);
        tree.insert(new Node(27), tree.root);
        tree.insert(new Node(55), tree.root);
        tree.inOrder(tree.root);
        System.out.println();
        tree.levelOrder(tree.root);
        System.out.println();

        System.out.println(tree.findOne(10));
        System.out.println(tree.findOne(33).value);
        System.out.println(tree.findOne(28) == null);

        tree.findAll(50).forEach(node -> System.out.print(node.value + " "));
        System.out.println();

        tree.delete(13);
        tree.delete(18);
        tree.delete(55);
        tree.inOrder(tree.root);
        System.out.println();
        tree.levelOrder(tree.root);
        System.out.println();
    }

    public boolean delete(int value) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            if (current.value == value) {
                break;
            }
            parent = current;
            if (current.value < value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.left != null && current.right != null) {
            Node rParent = current;
            Node rMin = current.right;
            while (rMin.left != null) {
                rParent = rMin;
                rMin = rMin.left;
            }
            current.value = rMin.value;
            parent = rParent;
            current = rMin;
        }

        // 要删除的节点只有一个子节点，或者没有子节点的情况，child可能是左右子节点或者null
        Node child;
        if (current.left != null) {
            child = current.left;
        } else if (current.right != null) {
            child = current.right;
        } else {
            child = null;
        }

        // 因为来到这里只可能有一个子节点或者没有子节点，不管当前要删除的当前节点的child是哪种情况，
        // 都可以直接赋值给parent节点指向当前节点的指针即可(null也可以理解成一种特殊的子节点的值)
        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        return true;
//        if (current.left == null && current.right == null) {
//            if (parent.left == current) {
//                parent.left = null;
//            } else {
//                parent.right = null;
//            }
//        }
//
//        if (current.left == null) {
//            if (parent.left == current) {
//                parent.left = current.right;
//            } else {
//                parent.right = current.left;
//            }
//        } else {
//            if (parent.left == current) {
//                parent.left = current.left;
//            } else {
//                parent.right = current.right;
//            }
//        }
    }

    public Node findOne(int value) {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                return node;
            }
            if (value >= node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public List<Node> findAll(int value) {
        List<Node> list = null;
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(node);
            }
            if (value >= node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return list;
    }

    public boolean insert2(Node node) {
        Node temp = root;
        if (root == null) {
            root = node;
            return true;
        }
        while (temp != null) {
            if (temp.value <= node.value) {
                if (temp.right == null) {
                    temp.right = node;
                    return true;
                }
                temp = temp.right;
            } else {
                if (temp.left == null) {
                    temp.left = node;
                    return true;
                }
                temp = temp.left;
            }
        }
        return false;
    }

    public boolean insert(Node node, Node temp) {
        if (root == null) {
            root = node;
            return true;
        }
        if (node.value >= temp.value) {
            if (temp.right == null) {
                temp.right = node;
                return true;
            }
            insert(node, temp.right);
        } else {
            if (temp.left == null) {
                temp.left = node;
                return true;
            }
            insert(node, temp.left);
        }
        return true;
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

    private static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
