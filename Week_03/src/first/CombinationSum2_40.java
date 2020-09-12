package first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2_40 {

    public static void main(String[] args) {
        CombinationSum2_40 combinationSum240 = new CombinationSum2_40();
        int[] candidates = {14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12};
        combinationSum240.combinationSum2(candidates, 27).forEach(list -> {
            list.forEach(num -> System.out.print(num + ""));
            System.out.println();
        });
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(result, candidates, new ArrayDeque<>(candidates.length), 0, target);
        return result;
    }

    public void dfs(List<List<Integer>> list, int[] candidates, ArrayDeque<Integer> deque, int begin, int target) {
        for (int i = begin; i < candidates.length; i++) {
            // 大剪枝：candidates[i] 大于target，后面的 candidates[i + 1]、candidates[i + 2] 肯定也大于target，因此用 break
            if (candidates[i] > target) {
                break;
            }
            // 小剪枝：跳过本层相同的节点
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            deque.offer(candidates[i]);
            // break会返回上一层，所以全局数据结构要先清除本层的状态
            // 这样写可以少一次递归调用，提前结束，但代码相对没这么优雅
            if (candidates[i] == target) {
                list.add(new ArrayList<>(deque));
                deque.removeLast();
                break;
            } else if (deque.size() == candidates.length) {
                deque.removeLast();
                break;
            }
            dfs(list, candidates, deque, i + 1, target - candidates[i]);
            deque.removeLast();
        }
    }
}
