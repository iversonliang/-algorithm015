package first;

public class FindMin_153 {

    public static void main(String[] args) {
        FindMin_153 findMin153 = new FindMin_153();
        System.out.println(findMin153.findMin(new int[]{5, 1, 2, 3, 4}));
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            if (nums[left] <= nums[mid]) {
                if (nums[left] > nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid - 1] < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
        }
        return nums[mid];
    }

    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

}
