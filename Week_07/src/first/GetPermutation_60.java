package first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

public class GetPermutation_60 {

    int count = 0;
    public static void main(String[] args) {
        GetPermutation_60 getPermutation_60 = new GetPermutation_60();
        System.out.println(getPermutation_60.getPermutation3(4, 9));
    }

    private int[] factorial;
    public String getPermutation3(int n, int k) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringBuilder path = new StringBuilder();
        return dfs3(n, k, 1, new boolean[n], path);
    }

    public String dfs3(int n, int k, int l, boolean[] visited, StringBuilder path) {
        if (l > n) {
            return path.toString();
        }

        int count = factorial[n - l];
        for (int i = 1; i <= n; i++) {
            if (visited[i - 1]) {
                continue;
            }
            // 如果count < k说明当前的节点i所有路径遍历完都还不到k，直接剪枝选下一个节点i+1
            if (count < k) {
                k -= count;
                continue;
            }

            // 来到这里说明肯定在当前节点i下得到第k个路径
            visited[i - 1] = true;
            path.append(i);
            // 选择当前节点i后，继续走去下一层的节点
            return dfs3(n, k, l + 1, visited, path);
        }
        return null;
    }

    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i-1] = i;
        }
        return dfs(n, new boolean[n], k, new ArrayDeque<>());
    }

    public String dfs(int n, boolean[] visited, int k, Deque<Integer> queue) {
        if (queue.size() == n) {
            if (++count == k) {
                StringBuilder sb = new StringBuilder();
                while (!queue.isEmpty()) {
                    sb.append(queue.poll());
                }
                return sb.toString();
            }
        }
        if (queue.size() > n) {
            return null;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i-1]) {
                continue;
            }
            queue.offer(i);
            visited[i-1] = true;
            String result = dfs(n, visited, k, queue);
            if (result != null) {
                return result;
            }
            queue.removeLast();
            visited[i-1] = false;
        }
        return null;
    }
}
