package homework.first;

import java.util.HashMap;
import java.util.Map;

public class Anagram_242
{

    public static void main(String[] args) {
        Anagram_242 anagram242 = new Anagram_242();
        System.out.println(anagram242.isAnagram3("anagram", "nagaram"));
        System.out.println(anagram242.isAnagram3("rat", "car"));
        System.out.println(anagram242.isAnagram3("a", "ab"));
    }

    public boolean isAnagram(String s, String t) {
        if (s != null && t != null && s.length() != t.length()) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String key = new String(new char[] {s.charAt(i)});
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            String key = new String(new char[] {t.charAt(i)});
                Integer count = map.containsKey(key) ? map.get(key) - 1 : -1;
                if (count == 0) {
                    map.remove(key);
                } else {
                    map.put(key, count);
                }
        }
        return map.isEmpty();
    }

    public boolean isAnagram2(String s, String t) {
        if (s != null && t != null && s.length() != t.length()) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) {
                continue;
            }
            String sKey = new String(new char[] {s.charAt(i)});
            Integer count = map.containsKey(sKey) ? map.get(sKey) + 1 : 1;
            if (count == 0) {
                map.remove(sKey);
            } else {
                map.put(sKey, count);
            }

            String tKey = new String(new char[] {t.charAt(i)});
            count = map.containsKey(tKey) ? map.get(tKey) - 1 : -1;
            if (count == 0) {
                map.remove(tKey);
            } else {
                map.put(tKey, count);
            }
        }

        return map.isEmpty();
    }

    public boolean isAnagram3(String s, String t) {
        if (s != null && t != null && s.length() != t.length()) {
            return false;
        }
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (sc == tc) {
                continue;
            }
            array[sc - 'a']++;
            array[tc - 'a']--;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
