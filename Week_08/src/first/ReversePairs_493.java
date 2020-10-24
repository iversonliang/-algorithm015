package first;

import java.util.Arrays;

/**
 * @create: 2020-10-24 17:14
 */
public class ReversePairs_493 {
    public static void main(String[] args) {
        int[] arr = {2,4,3,5,1};
        System.out.println(sort(arr));
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static int sort(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int left, int right) {
        if (right <= left) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int count = mergeSort(arr, left, mid) + mergeSort(arr, mid + 1, right);
        for (int l = left, r = mid + 1; l <= mid; l++) {
            while (r <= right && arr[l] > (long)arr[r] * 2) {
                r++;
            }
            count += r - mid - 1;
        }
        merge(arr, left, mid, right);
        return count;
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];
        int i = left;     // 左边数组的指针
        int j = mid + 1;  // 右边数组的指针
        int k = 0;        // 临时数组的指针

        // 合并，两边都还没遍历完
        while (i <= mid && j <= right) {
            tempArr[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        // 只剩下其中一边数组还没遍历完
        while (i <= mid) {
            tempArr[k++] = arr[i++];
        }
        while (j <= right) {
            tempArr[k++] = arr[j++];
        }

        // 将临时数组复制回原数组的[left, right]区间
        for (int n = 0; n < tempArr.length; n++) {
            arr[left + n] = tempArr[n];
        }
    }
}
