package first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Permute_46 {

    public static void main(String[] args) {
        Permute_46 permute46 = new Permute_46();
        int[] nums = {1, 2, 3};
        permute46.permute(nums).forEach(list -> {
            list.forEach(num -> System.out.print(num + ""));
            System.out.println();
        });
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayDeque<>(nums.length), new boolean[nums.length], nums);
        return result;
    }

    public void dfs(List<List<Integer>> result, ArrayDeque<Integer> deque, boolean[] usedArr, int[] nums) {
        if (nums.length == deque.size()) {
            result.add(new ArrayList<Integer>(deque));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (usedArr[i]) {
                continue;
            }
            deque.offer(nums[i]);
            usedArr[i] = true;
            dfs(result, deque, usedArr, nums);
            deque.removeLast();
            usedArr[i] = false;
        }
    }
}
