package first;

import java.util.Arrays;

public class PlusOne66 {

    public static void main(String[] args) {
        PlusOne66 plusOne66 = new PlusOne66();
        int[] nums = {8,9,9};
        Arrays.stream(plusOne66.plusOne3(nums)).forEach(num -> System.out.print(num + " "));
    }

    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }
        int i = digits.length - 2 < 0 ? 0 : digits.length - 2;
        for (; i >= 0; i--) {
            if (digits[i] != 9) {
                break;
            }
        }
        if (i < 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int j = 1; j <= digits.length; j++) {
                newDigits[j] = 0;
            }
            return newDigits;
        }
        digits[i]++;
        for (int j = i + 1; j < digits.length; j++) {
            digits[j] = 0;
        }
        return digits;
    }

    // 如果 元素 +1 == 10，要进位，将元素设为0，继续下一位
    // 如果 元素 != 0，代表进位已经完成，那么遍历就可以结束
    // 如果遍历完数组，代表原本第一位也是9，需要新建数组进位
    // 新建数组各元素默认是0，只需第一位设为1
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (++digits[i] == 10) {
                digits[i] = 0;
            }

            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    // 递归解法，如果i<0即遍历完了数组，代表需要新建数组进位，返回新数组，终止递归
    // 如果当前元素<9，就是不用进位操作，加1后也可以返回原数组，提前终止递归
    // 需要继续递归的只有一种情况，就是要进位，那么直接将本元素设为0，继续下一位的递归操作
    public int[] plusOne3(int[] digits) {
        return doPlus(digits, digits.length - 1);
    }

    public int[] doPlus(int[] digits, int i) {
        if (i < 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
        if (digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        digits[i] = 0;
        return doPlus(digits, i - 1);
    }
}
