package first;

import java.util.Arrays;

public class LengthOfLongestSubstring_3 {

    public static void main(String[] args) {
        LengthOfLongestSubstring_3 lengthOfLongestSubstring3 = new LengthOfLongestSubstring_3();
        System.out.println(lengthOfLongestSubstring3.lengthOfLongestSubstring2("aab"));
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] arr = new int[256];
        Arrays.fill(arr, -1);
        int temp = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            int index = arr[s.charAt(j)];
            arr[s.charAt(j)] = j;
            if (index == -1) {
                temp++;
                continue;
            }
            max = Math.max(j - i, max);
            i = Math.max(i, index + 1);
            temp = j - i + 1;
        }
        return Math.max(temp, max);
    }

    public int lengthOfLongestSubstring2(String s) {
        int max = 0;
        int[] arr = new int[256];
        Arrays.fill(arr, -1);
        for (int i = 0, j = 0; j < s.length(); j++) {
            // 当前字符的存储索引位置，不为-1即当前字符重复出现
            int oldIndex = arr[s.charAt(j)];
            arr[s.charAt(j)] = j;
            if (oldIndex != -1) {
                // 当前字符重复出现需要收缩窗口左边界，但不能比当前下标值小
                i = Math.max(i, oldIndex + 1);
            }
            // 每次遍历都计算最大的距离，大于max就更新max的值
            max = Math.max(j - i + 1, max);
        }
        return max;
    }
}
