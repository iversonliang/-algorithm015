package first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique_47 {

    public static void main(String[] args) {
        PermuteUnique_47 permute46 = new PermuteUnique_47();
        int[] nums = {1, 1, 2};
        permute46.permuteUnique(nums).forEach(list -> {
            list.forEach(num -> System.out.print(num + ""));
            System.out.println();
        });
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 因为数组的元素是可以重复的，所以这里做预处理先对数组进行排序，让重复的数据在一起，后续方便判断进行剪枝
        Arrays.sort(nums);
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

            // !usedArr[i - 1]剪枝的含义，以下基于连续3个1说明
            // 1、对于同一层的情况
            // 对于同一层3个1的这3个兄弟节点来说，路径选择第2个1的时候，第1个1由于已经恢复现场，所以是没有使用过的，
            // 这一句判断的意思就是：对于当前元素（第2个1），发现前面的元素是一样并且还没有选过，就不选择当前这条路走了
            // 因为第2个1的这条路，实际上和第1个1的路是完全相同的，走第1条就够了，重复的路直接剪掉
            // 第3个1跟第2个同理：发现和前面第2个1相同，并且前面的第2个1没有走，当前第3个1这条路也不走
            //
            // 2、对于不同层的情况（递归到下一层）
            // 这种情况就是上一层父节点选择了第1个1，当前层选择了第2个1时，发现自己是第2个1虽然跟前面都是1，
            // 但因为在父节点层已经走过了，相当于这里是新的一条路，没有重复是可以走的。
            // 在本层走完第2个1，恢复现场，继续走第3个1时，情况和之前同一层相同。发现前面第2个1还没有走，当前是重复的路不走
            if (i > 0 && nums[i] == nums[i - 1] && !usedArr[i - 1]) {
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
