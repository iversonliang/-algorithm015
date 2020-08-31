package first;

import java.util.Arrays;

public class MoveZero283 {

    public static void main(String[] args) {
        int[] nums = {1,0,1};
        MoveZero283 moveZero283 = new MoveZero283();
        moveZero283.moveZeroes(nums);
        Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
    }

    // 如果都没有遇到0，快慢指针会一直一致遍历完
    // 如果遇到0，快指针直接往后找可以写的值，给慢指针，而慢指针一直在0的位置等待
    // 覆盖写入完成后，快指针将元素改为0
    public void moveZeroes(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (i != p) {
                nums[p] = nums[i];
                nums[i] = 0;
            }
            p++;
        }
    }
}
