package first;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevels_637 {

    public static void main(String[] args) {
        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode20 = new TreeNode(20);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode20.left = treeNode15;
        treeNode20.right = treeNode7;

        treeNode3.left = treeNode9;
        treeNode3.right = treeNode20;

        AverageOfLevels_637 average = new AverageOfLevels_637();
        average.averageOfLevels2(treeNode3).forEach(d -> System.out.print(d + " "));
    }

    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode temp;
        List<Double> list = new ArrayList<>();
        int nextLevel = 0;
        int nextMax = 0;
        int currentMax = 1;
        int currentLevel = 1;
        double sum = 0;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            sum += temp.val;
            if (temp.left != null) {
                queue.offer(temp.left);
                nextLevel++;
                nextMax++;
            }
            if (temp.right != null) {
                queue.offer(temp.right);
                nextLevel++;
                nextMax++;
            }
            if (--currentLevel == 0) {
                list.add((double) sum / currentMax);
                currentMax = nextMax;
                currentLevel = nextLevel;
                nextMax = 0;
                nextLevel = 0;
                sum = 0;
            }
        }
        return list;
    }

    public List<Double> averageOfLevels2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Double> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            double sum = 0;
            for (int i = 0; i < levelCount; i++) {
                TreeNode temp = queue.poll();
                sum += temp.val;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            result.add(sum / levelCount);
        }
        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
