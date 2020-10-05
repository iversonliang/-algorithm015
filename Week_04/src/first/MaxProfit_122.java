package first;

public class MaxProfit_122 {

    public static void main(String[] args) {
        MaxProfit_122 maxProfit122 = new MaxProfit_122();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit122.maxProfit3(prices));
    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0; // 持有现金（现金收益）
        dp[0][1] = -prices[0]; // 持有股票

        for (int i = 1; i < prices.length; i++) {
            // 当天持有现金时的最大收益，只能是 前一天只持有现金，或者前一天持有的股票今天卖出 两种状态转换来
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            // 当天持有股票时的最大收益，只能是 前一天买入了股票，或者前一天就持有股票 两种装转换来
            dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
        }
        // 最后一天肯定是持有现金比持有股票的利润大（最后一天还持有股票，相当于利润还没套现）
        return dp[prices.length-1][0];
    }

    public int maxProfit2(int[] prices) {
        int preCash = 0;
        int preStock = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            preCash = Math.max(preCash, preStock + prices[i]);
            preStock = Math.max(preCash - prices[i], preStock);
        }
        return preCash;
    }

    public int maxProfit3(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i <= prices.length - 1; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
