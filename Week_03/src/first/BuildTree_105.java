package first;

import java.util.HashMap;
import java.util.LinkedList;

public class BuildTree_105 {

    public static void main(String[] args) {
        BuildTree_105 buildTree105 = new BuildTree_105();
        int[] prorder = {1,2};
        int[] inorder = {2,1};
        TreeNode root = buildTree105.buildTree(prorder, inorder);
        buildTree105.levelOrder(root);
    }

    // 按层遍历
    public void levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            System.out.print(node.val + " ");
        }
    }

    int[] preOrder;
    int[] inOrder;
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        preOrder = preorder;
        inOrder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(0, map.get(preorder[0]), inorder.length, 0);
    }

    public TreeNode build(int inLeftStart, int inRoot, int inRightEnd, int preRoot) {
        if (inLeftStart == inRightEnd) {
            return null;
        }
        TreeNode current = new TreeNode(inOrder[inRoot]);
        int currLeftLength = inRoot - inLeftStart;
        int nextLeftPreRoot = preRoot + 1;
        int nextRightPreRoot = nextLeftPreRoot + currLeftLength;
        if (nextLeftPreRoot < preOrder.length) {
            int nextLeftInRoot = map.get(preOrder[nextLeftPreRoot]);
            current.left = build(inLeftStart, nextLeftInRoot, inRoot, nextLeftPreRoot);
        }
        if (nextRightPreRoot < preOrder.length) {
            int nextRightInRoot = map.get(preOrder[nextRightPreRoot]);
            current.right = build(inRoot + 1, nextRightInRoot, inRightEnd, nextRightPreRoot);
        }
        return current;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
