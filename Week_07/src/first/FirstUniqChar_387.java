package first;

import java.util.*;

public class FirstUniqChar_387 {

    public static void main(String[] args) {
        FirstUniqChar_387 firstUniqChar387 = new FirstUniqChar_387();
        System.out.println(firstUniqChar387.firstUniqChar("z"));
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c  = s.charAt(i);
            int times = map.getOrDefault(c, 0) + 1;
            map.put(c, times);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    public int firstUniqChar2(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
