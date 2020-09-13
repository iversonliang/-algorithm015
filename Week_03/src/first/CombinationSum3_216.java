package first;

import java.util.*;

public class CombinationSum3_216 {

    public static void main(String[] args) {
        CombinationSum3_216 combinationSum3216 = new CombinationSum3_216();
        combinationSum3216.combinationSum3(3, 9).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>(k);
        dfs(result, queue, n, k, 1);
        return result;
    }

    public void dfs(List<List<Integer>> result, Deque<Integer> deque, int n, int k, int start) {
        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(deque));
            return;
        }
        if (k == 0 || n <= 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            deque.offer(i);
            dfs(result, deque, n - i, k - 1, i + 1);
            deque.removeLast();
        }
    }
}
