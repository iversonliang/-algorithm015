package homework.first;

import java.util.*;

public class GroupAnagrams_49 {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams_49 groupAnagrams = new GroupAnagrams_49();
        List<List<String>> list = groupAnagrams.groupAnagrams(strs);
        list.forEach(temp -> {
            temp.forEach(str -> System.out.print(str + " "));
            System.out.println();
        });
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = serialize(str);
            List<String> list;
            if (!map.containsKey(key)) {
                list = new ArrayList<>();
            } else {
                list = map.get(key);
            }
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public String serialize(String str) {
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            sb.append("#").append(arr[i]);
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> list;
            if (!map.containsKey(key)) {
                list = new ArrayList<>();
            } else {
                list = map.get(key);
            }
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
