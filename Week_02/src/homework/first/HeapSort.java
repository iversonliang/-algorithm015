package homework.first;

import java.util.Arrays;

public class HeapSort {

    public int[] elements;

    public static void main(String[] args) {
        int arr[] = {110, 100, 90,40, 80, 20, 60, 10, 30, 50, 70};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static void sort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapifyDown(arr, i,0);
        }
    }

    public static void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            heapifyDown(arr, arr.length, i);
        }
    }

    // 排序时，已经排序到队尾的不能被修改，要用n限制前面需要堆化操作的元素个数
    public static void heapifyDown(int[] arr, int n, int i) {
        int temp = arr[i];
        int current = i;
        // 没有子节点不进入循环
        while (getLeftIndex(current) < n) {
            int left = getLeftIndex(current);
            int right = getRightIndex(current);

            // 当前节点大于2个子节点，直接结束
            if (left < n && temp > arr[left] && right < n && temp > arr[right]) {
                break;
            }

            int child = -1;
            if (left < n && arr[left] > temp) {
                child = left;
            }
            if (right < n && arr[right] > (child != -1 ? arr[child] : temp)) {
                child = right;
            }

            // 小于其中一个子节点
            if (child != -1) {
                arr[current] = arr[child];
                current = child;
            } else {
                // 没有右节点，并且大于左节点，直接结束
                break;
            }
        }
        arr[current] = temp;
    }

    public static int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    public static int getRightIndex(int i) {
        return 2 * i + 2;
    }

    public static int getParentIndex(int i) {
        return (i - 1) / 2;
    }

}
