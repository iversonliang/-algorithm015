package homework.first;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {

    public static void main(String[] args) {
        TwoSum_1 twoSum = new TwoSum_1();
        Arrays.stream(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9)).forEach(num -> System.out.print(num + " "));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
