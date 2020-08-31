package first;

import java.util.Arrays;

public class MergeArray88 {

    public static void main(String[] args) {
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        MergeArray88 mergeArray88 = new MergeArray88();
        mergeArray88.merge4(nums1, 1, nums2, 1);
        Arrays.stream(nums1).forEach(num -> System.out.print(num + " "));
    }

    // 最简单的先合并数组再排序
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    // 双指针从前往后遍历，需要将nums1数组的前m个数另外先存起来，不然将会被覆盖
    // 遍历时如果其中一个数组已经遍历完，直接遍历另外的数组即可
    // 如果都没有遍历完就要比较
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        for (int i = 0; i < m; i++) {
            temp[i] = nums1[i];
        }

        int p2 = 0;
        int pt = 0;
        for (int p1 = 0; p1 < m + n; p1++) {
            if (p2 != n && pt != m) {
                nums1[p1] = nums2[p2] < temp[pt] ? nums2[p2++] : temp[pt++];
                continue;
            }
            nums1[p1] = p2 == n ? temp[pt++] : nums2[p2++];
        }
    }

    // 双指针从后开始遍历，思路和前面几乎一样，只是少了额外的空间复制m个元素
    // 注意从后遍历的话越界的情况，包括下标-1和读取最后一个元素时
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m;
        int p2 = n;
        for (int p = m + n - 1; p >= 0; p--) {
            // 优化到第三版实现了一行代码解决，还是挺有成就感
            // 从逻辑上说，就是每次赋值，肯定是其中一个数组进行，其数组指针减1，num1指针肯定也减1
            // 如果p2 == 0，那么num2数组已经遍历完，需要num1赋值
            // 如果p1 > 0代表num1还没遍历完（有可能一开始num1就没有值），并且num1 > num2的值，也是num1赋值
            nums1[p] = (p2 == 0 || (p1 > 0 && nums1[p1 - 1] > nums2[p2 - 1])) ? nums1[--p1] : nums2[--p2];
        }
    }

    // 递归写法，整个过程要不就是num1赋值，要不就是num2赋值，只要指针P还没到数组头就还没完成合并
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        doMerge(nums1, m, nums2, n, m + n);
    }

    public  void doMerge(int[] nums1, int p1, int[] nums2, int p2, int p) {
        if (p == 0) {
            return;
        }
        boolean isMoveNum1 = p2 == 0 || (p1 > 0 && nums1[p1 - 1] > nums2[p2 - 1]);
        nums1[--p] = isMoveNum1 ? nums1[--p1] : nums2[--p2];
        doMerge(nums1, p1, nums2, p2, p);
    }
}
