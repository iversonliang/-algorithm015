package first;

import java.util.Arrays;

/**
 * @create: 2020-10-24 18:02
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {2, 15, 6, 1, 9, 7, 8, 6};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        // 中轴左边的都比它小，右边的都比它大，说明位置已经确定，只需递归排序中轴两边的元素
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int count = left;
        int pivot = right;
        for (int i = left; i < right; i++) {
            // 如果当前的元素 < 中轴元素，那么就将当前元素交换到count的位置
            // 这里先不用管当前count位置的元素大小，即使当前count位置的元素比中轴元素小，
            // 最终也会被交换回新的中轴左边，因此遍历会将所有小于中轴的元素都交换到左边，
            // 最终的效果就是元素[0, count]区间都是小于中轴的元素，count++就是新的中轴位置
            if (arr[i] < arr[pivot]) {
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;
                count++;
            }
        }
        // 将中轴元素交换到count的位置上
        int temp = arr[pivot];
        arr[pivot] = arr[count];
        arr[count] = temp;
        return count;
    }
}
