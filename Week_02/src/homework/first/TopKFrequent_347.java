package homework.first;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class TopKFrequent_347 {

    public static void main(String[] args) {
        TopKFrequent_347 topKFrequent347 = new TopKFrequent_347();
        int[] nums = {1, 1, 2, 2, 1, 4, 3};
        Arrays.stream(topKFrequent347.topKFrequent(nums, 2)).forEach(num -> System.out.print(num + " "));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                pq.offer(entry.getKey());
            } else {
                if (entry.getValue() > map.get(pq.peek())) {
                    pq.remove();
                    pq.offer(entry.getKey());
                }
            }
        }
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = pq.poll();
        }
        return arr;
    }
}
