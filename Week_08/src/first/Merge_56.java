package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @create: 2020-10-25 00:12
 */
public class Merge_56 {

    public static void main(String[] args) {
        Merge_56 merge56 = new Merge_56();
        int[][] intervals = {{1,3},{8,10},{2,6},{15,18}};
        Arrays.stream(merge56.merge(intervals)).forEach(arr -> {
            System.out.println(arr[0] + " " + arr[1]);
        });
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a1, a2) -> a1[0] - a2[0]);
        List<int[]> list = new ArrayList<>();
        int[] last = null;
        for (int[] arr : intervals) {
            if (last == null || arr[0] > last[1]) {
                list.add(arr);
                last = arr;
            } else {
                last[1] = Math.max(arr[1], last[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
