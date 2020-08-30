package first;

import java.util.Arrays;

public class Question189 {

    public static void main(String[] args) {
        Question189 question = new Question189();
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        question.rotate3(nums, 3);
        Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
    }

    // 踩坑记录：
    // 一开始想着是从第一个元素开始，直接定位到最终的位置，然后通过swap操作完成，然后数组整体“往右移”
    // 但是最终发现是不能通过O(1)的空间复杂度完成的，比如一个临时变量，因为对于Min(k, n-k)个元素最终会放在
    // 已经移动完的空位上，但是遍历到这些Min(k, n-k)个元素时，比如n=7, k=5, 即6和7这2个元素原本的位置的值
    // 因为已经被覆盖，是没办法能得到的，除非再用额外的空间存储这Min(k, n-k)个元素，但这样就不是O(1)的空间了


    // 对于一个长度为n的数组，整体移动k个位置
    // 1、当n和k的最大公约数 等于1的时候：1 次遍历就可以完成交换；比如 n = 5, k = 3
    // 2、当n和k的最大公约数 不等于1的时候：1 次遍历是无法完成的所有元素归位，需要m(最大公约数) 轮
    //       比如n=6,k=4,就需要2轮，分别是A C E 和 B D F 两组元素
    //
    // 方法实现总结：
    // 这个方法比较容易遗漏的就是有非1的最大公约数情况，还有就是怎么通过一段代码将2种情况融合，2点总结如下
    // 1、这个方法里面使用count来保证最终移动了这么多次就可以
    // 2、这里外层循环相当于是多少组，每一个i的值相当于就是每一组元素的遍历入口，结束条件是所有元素都移动过，
    //      即count的次数满了
    public void rotate(int[] nums, int k) {
        int temp;
        int count = 0;
        for (int i = 0; count < nums.length - 1; i++) {
            int curr = i;
            int pre = nums[curr];

            do {
                int newIndex = (curr + k) % nums.length;
                temp = nums[newIndex];
                nums[newIndex] = pre;
                curr = newIndex;
                pre = temp;
                count++;
            } while (i != curr);
        }
    }

    public void rotate2(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            doRotate2(nums);
        }
    }

    // 这里移动单个元素的方法，这样写法不够简洁，还要判断下标，循环退出类似于上面的解法的思路，
    // 也是用count来保证，如果不用的话会因为下标的操作会造成死循环。
    public void doRotate(int[] nums) {
        int temp;
        int pre = nums[0];
        int count = 0;
        for (int i = 0; count < nums.length; count++) {
            i = i == nums.length - 1 ? -1 : i;
            temp = nums[++i];
            nums[i] = pre;
            pre = temp;
        }
    }

    // 这个实现的一个细节点就是利用temp先取最后一个元素放到最前面开始操作，避免了下标的判断操作
    public void doRotate2(int[] nums) {
        int temp;
        int pre = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            temp = nums[i];
            nums[i] = pre;
            pre = temp;
        }
    }

    // 反转链表这种思路，自己是肯定想不到的了，背诵吧。使用数组反转实现确实很简单
    public void rotate3(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        int newIndex = k % nums.length;
        reverse(nums, 0, newIndex - 1);
        reverse(nums, newIndex, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        for (;start < end; start++, end--) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
        }
    }
}
