package second;

import java.util.Arrays;

/**
 * @create: 2020-10-24 23:38
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {2,15,6,1,9,7,8,6,4,7,10,20,21,18,20,26,23,15,17,10,6,2,1,29,30,20,53,42,38,2,24,4,5,8,11,14};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
        System.out.println();
        System.out.println("total:" + arr.length);
    }

    public static void sort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i >=0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapifyDown(arr, i, 0);
        }
    }

    public static void heapifyDown(int[] arr, int limit, int i) {
        int value = arr[i];
        int tempIdx = i;
        for (int child = 2 * i + 1; child < limit; child = 2 * child + 1) {
            if (child + 1 < limit && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[child] > value) {
                arr[tempIdx] = arr[child];
                tempIdx = child;
            }
        }
        arr[tempIdx] = value;
    }

    public static void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, arr.length, i);
        }
    }
}
