package homework.first;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class NthUglyNumber_49 {

    public static void main(String[] args) {
        NthUglyNumber_49 nthUglyNumber49 = new NthUglyNumber_49();
        System.out.println(nthUglyNumber49.nthUglyNumber2(1690));
    }

    public int nthUglyNumber(int n) {
        long[] arr = {2, 3, 5};
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        pq.add(1L);

        long num = 0;
        for (int i = 0; i < n; i++) {
            num = pq.poll();
            for (long factor : arr) {
                if (!set.contains(num * factor)) {
                    pq.offer(num * factor);
                    set.add(num * factor);
                }
            }
        }
        return (int) num;
    }

    public int nthUglyNumber2(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(arr[p2] * 2, Math.min(arr[p3] * 3, arr[p5] * 5));
            if (arr[i] == arr[p2] * 2) {
                p2++;
            }
            if (arr[i] == arr[p3] * 3) {
                p3++;
            }
            if (arr[i] == arr[p5] * 5) {
                p5++;
            }
        }
        return arr[n - 1];
    }
}
