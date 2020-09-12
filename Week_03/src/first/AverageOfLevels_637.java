package first;

import java.lang.reflect.Array;
import java.util.*;

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
        average.averageOfLevels3(treeNode3).forEach(d -> System.out.print(d + " "));
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

    public List<Double> averageOfLevels3(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Map<Integer, Double> countMap = new HashMap<>();
        Map<Integer, Double> sumMap = new HashMap<>();
        dfs(sumMap, countMap, root, 1);
        for (Map.Entry<Integer, Double> entry : sumMap.entrySet()) {
            result.add(entry.getValue() / countMap.get(entry.getKey()));
        }
        return result;
    }

    public void dfs(Map<Integer, Double> sumMap, Map<Integer, Double> countMap, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (sumMap.containsKey(level)) {
            sumMap.put(level, sumMap.get(level) + node.val);
            countMap.put(level, countMap.get(level) + 1);
        } else {
            sumMap.put(level, (double) node.val);
            countMap.put(level, 1D);
        }
        dfs(sumMap, countMap, node.left, level + 1);
        dfs(sumMap, countMap, node.right, level + 1);
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
