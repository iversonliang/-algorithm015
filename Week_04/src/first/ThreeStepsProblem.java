package first;

public class ThreeStepsProblem {

    public static void main(String[] args) {
        ThreeStepsProblem threeStepsProblem = new ThreeStepsProblem();
        System.out.println(threeStepsProblem.waysToStep2(1));
    }

    public int waysToStep(int n) {
        long[] temp = new long[] {1, 2, 4};
        if (n <= 3) {
            return (int) temp[n - 1];
        }
        long result = 0;
        for (int i = 4; i <=n; i++) {
            result = (temp[0] + temp[1] + temp[2]) % 1000000007;
            temp[0] = temp[1];
            temp[1] = temp[2];
            temp[2] = result;
        }
        return (int) result;
    }

    public int waysToStep2(int n) {
        long[] temp = new long[n];
        if (n < 4) {
            temp = new long[3];
        }
        temp[0] = 1;
        temp[1] = 2;
        temp[2] = 4;
        for (int i = 4; i <=n; i++) {
            temp[i - 1] = (temp[i - 2] + temp[i - 3] + temp[i - 4]) % 1000000007;
        }
        return (int) temp[n - 1];
    }
}
