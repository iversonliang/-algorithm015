package first;

public class ClimbStairs_70 {

    public static void main(String[] args) {
        ClimbStairs_70 climbStairs70 = new ClimbStairs_70();
        System.out.println(climbStairs70.climbStairs3(1));
    }

    int[] nums = null;

    public int climbStairs(int n) {
        if (nums == null) {
            nums = new int[n];
            nums[0] = 1;
            if (n > 1) {
                nums[1] = 2;
            }
        }
        if (nums[n - 1] != 0) {
            return nums[n - 1];
        }

        int n2 = climbStairs(n - 2);
        if (n > 4) {
            nums[n - 2 - 1] = n2;
        }
        int n1 = climbStairs(n - 1);
        if (n > 3) {
            nums[n - 1 - 1] = n1;
        }
        return n1 + n2;
    }

    public int climbStairs3(int n) {
        if (n <= 2) {
            return n;
        }
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        return doClimb(n, arr);
    }

    public int doClimb(int n, int[] nums) {
        if (nums[n - 1] != 0) {
            return nums[n - 1];
        }
        int result = doClimb(n - 1, nums) + doClimb(n - 2, nums);
        nums[n - 1] = result;
        return result;
    }

    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int n1 = 2;
        int n2 = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = n1 + n2;
            n2 = n1;
            n1 = result;
        }
        return result;
    }
}
