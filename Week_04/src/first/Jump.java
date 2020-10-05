package first;

public class Jump {

    public static void main(String[] args) {
        Jump jump = new Jump();
        int[] nums = {2,3,1,1,4};
        System.out.println(jump.jump2(nums));
    }

    public int jump2(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length) {
                    return dp[nums.length - 1];
                }
                dp[i + j] = Math.min(dp[i+j], dp[i] + 1);
            }
        }
        return dp[nums.length - 1];
    }

    public int jump(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = 1;

        for (int i = 1; i <= nums.length - 1; i++) {
            boolean isJump = false;
            if (i + nums[i] > dp[i-1][0]) {
                isJump = true;
                dp[i][0] = i + nums[i];
            } else {
                dp[i][0] = dp[i-1][0];
            }

            if (!isJump) {
                dp[i][1] = dp[i-1][1];
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j <= i; j++) {
                    if (dp[j][0] >= i && dp[j][1] > 0 && dp[j][1] < min) {
                        min = dp[j][1];
                    }
                }
                dp[i][1] = min + 1;
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (dp[i][0] >= nums.length - 1) {
                result = dp[i][1];
                break;
            }
        }
        return result;
    }
}
