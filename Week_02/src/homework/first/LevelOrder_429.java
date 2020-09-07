package homework.first;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LevelOrder_429 {

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
            Node temp;
            queue.offer(root);
            int currCount = 1;
            int nextCount = 0;
            int level = 1;
            result.add(new ArrayList<Integer>());
            while (!queue.isEmpty()) {
                temp = queue.poll();
                result.get(level - 1).add(temp.val);
                currCount--;
                if (temp.children != null && temp.children.size() > 0) {
                    for (Node child : temp.children) {
                        queue.offer(child);
                        if (++nextCount == 1) {
                            result.add(new ArrayList<Integer>());
                        }
                    }
                }
                if (currCount == 0) {
                    currCount = nextCount;
                    nextCount = 0;
                    level++;
                }
            }
            return result;
        }

        public List<List<Integer>> levelOrder2(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                // 这里queue的size是动态变化的，不能直接用在循环的终止条件判断中
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    list.add(node.val);
                    queue.addAll(node.children);
                }
                result.add(list);
            }
            return result;
         }
    }

    class Node {
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
    };
}
