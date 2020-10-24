package first;

import java.util.Arrays;

/**
 * @create: 2020-10-24 16:23
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {2,15,6,1,9,7,8,6};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i == min) {
                continue;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}
