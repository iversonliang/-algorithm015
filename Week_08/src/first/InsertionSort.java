package first;

import java.util.Arrays;

/**
 * @create: 2020-10-24 16:33
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {2,15,6,1,9,7,8,6};
        sort(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i+1];
            for (int j = i; j >= 0; j--) {
                if (temp > arr[j]) {
                    break;
                }
                arr[j+1] = arr[j];
                if (j == 0 || temp > arr[j-1]) {
                    arr[j] = temp;
                    break;
                }
            }
        }
    }
}
