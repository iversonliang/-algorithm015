package first;

public class NumDecodings_91 {

    public static void main(String[] args) {
        NumDecodings_91 numDecodings91 = new NumDecodings_91();
        System.out.println(numDecodings91.numDecodings2("012"));
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        dp[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        if (s.length() == 1) {
            return dp[s.length() - 1];
        }
        if (s.charAt(s.length() - 2) == '0') {
            dp[s.length() - 2] = 0;
        } else if (isLetterRange(s, s.length() - 2, s.length() - 1)) {
            dp[s.length() - 2] = dp[s.length() - 1] + 1;
        } else if (s.charAt(s.length() - 1) == '0') {
            dp[s.length() - 2] = 0;
        } else {
            dp[s.length() - 2] = 1;
        }

        for (int i = s.length() - 3; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }

            if (isLetterRange(s, i, i + 1)) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public boolean isLetterRange(String s, int index1, int index2) {
        int ten = (s.charAt(index1) - '0') * 10;
        int one = s.charAt(index2) - '0';
        return ten + one <= 26;
    }

    public int numDecodings3(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        dp[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        if (s.length() == 1) {
            return dp[s.length() - 1];
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }

            if (isLetterRange(s, i, i + 1)) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public int numDecodings2(String s) {
        int temp2 = 1;
        int temp1 = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        if (s.length() == 1) {
            return temp1;
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                temp2 = temp1;
                temp1 = 0;
                continue;
            }

            int temp = temp1;
            if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26) {
                temp = temp1 + temp2;
            }
            temp2 = temp1;
            temp1 = temp;
        }
        return temp1;
    }


}
