package first;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestor_236 {

    // root是每次递归调用时的当前节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftResult = root.left != null ? lowestCommonAncestor(root.left, p, q) : null;
        TreeNode rightResult = root.right != null ? lowestCommonAncestor(root.right, p, q) : null;
        // p,q两个节点都找不到
        if (leftResult == null && rightResult == null) {
           return null;
        }
        // 左子树没找到，都在右子树
        if (leftResult == null) {
            return rightResult;
        }
        // 右子树没找到，都在左子树
        if (rightResult == null) {
            return leftResult;
        }
        // 分别在左右子树找到
        return root;
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
