package first;

import java.util.Arrays;

public class BinarySearch_33 {

    public static void main(String[] args) {
        BinarySearch_33 binarySearch33 = new BinarySearch_33();
        int[] nums = {3,1};
        System.out.println(binarySearch33.search2(nums, 1));
    }

    public int search(int[] nums, int target) {
        int startIndex = 0;
        for (int i = 1; i <= nums.length - 1; i++) {
            if (nums[i] < nums[i - 1]) {
                startIndex = i;
                break;
            }
        }
        int[] array = new int[nums.length];
        for (int i = startIndex; i <= nums.length - 1; i++) {
            array[i - startIndex] = nums[i];
        }
        for (int i = 0; i < startIndex; i++) {
            array[nums.length - startIndex + i] = nums[i];
        }

        int left = 0;
        int right = array.length - 1;

        int arrayIndex = -1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (array[mid] == target) {
                arrayIndex = mid;
                break;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (arrayIndex == -1) {
            return -1;
        }
        return (arrayIndex + startIndex) % nums.length;
    }

    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 至少有一边是有序的
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 这个<=是关键，<会出Bug
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
