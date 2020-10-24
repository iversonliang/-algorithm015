package first;

import java.util.Arrays;

/**
 * @create: 2020-10-24 16:09
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {2,15,6,1,9,7,8,6};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i ; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
