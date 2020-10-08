package homework.first;

import java.util.Arrays;

public class HeapSort {

    public int[] elements;

    public static void main(String[] args) {
        int arr[] = {3,1,2,4,6,9,8,7,5};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static void heapifyDown3(int[] arr, int limit, int i) {
        int value = arr[i];
        int tempIndex = i;
        for (int child = 2 * i + 1; child < limit; child += 2 * i + 1) {
            if (child + 1 < limit && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[child] > value) {
                arr[tempIndex] = arr[child];
                tempIndex = child;
            } else {
                break;
            }
        }
        arr[tempIndex] = value;
    }

    public static void sort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            // 建好堆后，第一个元素就是堆顶元素，直接跟最后一个元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 重新堆化[0,i)的元素，得到新的堆顶（最大值）
            heapifyDown3(arr, i, 0);
        }
    }

    public static void buildHeap(int[] arr) {
        // 这里是从0下标开始，所以从 [0, size / 2 - 1] 开始堆化
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            // 每次插入元素，重新堆化整个堆，得到新的堆顶元素（最大值）
            heapifyDown3(arr, arr.length, i);
        }
    }

    // 排序时，已经排序到队尾的不能被修改，要用n限制前面需要堆化操作的元素个数
    public static void heapifyDown(int[] arr, int n, int i) {
        int value = arr[i];
        int index = i;

        int left;
        // 没有子节点不进入循环
        while ((left = getLeftIndex(index)) < n) {
            int right = getRightIndex(index);

            // 当前节点大于2个子节点，直接结束
            if (value > arr[left] && right < n && value > arr[right]) {
                break;
            }

            int child = -1;
            // 比较左子节点，如果左子节点比当前值大，选择左子节点
            if (arr[left] > value) {
                child = left;
            }
            // 右子节点需要比较，并且右子节点最大，选择右子节点
            if (right < n && arr[right] > arr[left] && arr[right] > value) {
                child = right;
            }

            // 小于其中一个子节点
            if (child != -1) {
                arr[index] = arr[child];
                index = child;
            } else {
                // 没有右节点，并且大于左节点，直接结束
                break;
            }
        }
        arr[index] = value;
    }


    public static void heapifyDown2(int[] arr, int n, int index) {
        int value = arr[index];
        int tempIndex = index;
        int left;
        while ((left = getLeftIndex(tempIndex)) < n) {
            int right = getRightIndex(tempIndex);
            int leftValue = arr[left];
            int rightValue = right < n ? arr[right] : -1;

            // 只有左子节点，即最后一轮，无论是否交换都终止
            if (rightValue == -1) {
                if (value < leftValue) {
                    arr[tempIndex] = arr[left];
                    tempIndex = left;
                }
                break;
            }
            // 如果大于其左右子节点，直接终止
            if (value > leftValue && value > rightValue) {
                break;
            }

            if (leftValue > rightValue) {
                arr[tempIndex] = leftValue;
                tempIndex = left;
            } else {
                arr[tempIndex] = rightValue;
                tempIndex = right;
            }
        }
        arr[tempIndex] = value;
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
