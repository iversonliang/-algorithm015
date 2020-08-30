package first;

public class Question26 {

    public static void main(String[] args) {
        Question26 question = new Question26();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int num = question.removeDuplicates(nums);
        System.out.println(num);
        for (int i = 0; i < num; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 1; i <= nums.length - 1; i++) {
            // 找到新的不重复元素
            if (nums[i] != nums[j]) {
                // 新的元素存储在当前慢指针j的下一个数组下标位置
                nums[++j] = nums[i];
            }
        }
        // j是存储（操作）的次数，因此需要加上原来的1个元素，才是队列中不重复的元素
        return ++j;
    }

}
