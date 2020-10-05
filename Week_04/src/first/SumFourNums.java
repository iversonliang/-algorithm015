package first;

import java.util.*;

public class SumFourNums {

    public static void main(String[] args) {
        SumFourNums sumFourNums = new SumFourNums();
        int[] nums = {1, 0, -1, 0, -2, 2};
        sumFourNums.fourSum(nums, 0).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(result, new ArrayDeque<Integer>(5), 0, nums, 0, target);
        return result;
    }

    public void dfs(List<List<Integer>> result, Deque<Integer> deque, int sum, int[] nums, int i, int target) {
        if (deque.size() > 4 || (i + 4 - deque.size()) > nums.length) {
            return;
        }
        if (deque.size() == 4) {
            if (sum != target) {
                return;
            }
            List<Integer> list = new ArrayList<>(deque);
            result.add(list);
            return;
        }

        // 不选当前数值
        dfs(result, deque, sum, nums, i+1, target);

        // 选当前数值
        deque.offer(nums[i]);
        dfs(result, deque, sum + nums[i], nums, i+1, target);
        deque.pollLast();

    }
}
