package edu.study.dp;

import java.util.*;

public class StoneGame2 {
    private int[][] dp;
    private int[] suffixSum, arr;
    private int size;

    public int stoneGameII(int[] piles) {
        arr = piles;
        size = piles.length;

        suffixSum = new int[size];
        suffixSum[size-1] = arr[size-1];

        for (int i = size-2; i>=0; i--) suffixSum[i] = suffixSum[i-1] + arr[i];

        dp = new int[size][size];
        for (int[] row : dp) Arrays.fill(row, -1);

        return dfs(0, 1);
    }

    private int dfs(int start, int m) {
        if (start >= size) return 0;
        if (m*2 >= size-start) return suffixSum[start];
        if (dp[start][m] > -1) return dp[start][m];

        int curSum = 0, curMaxSum = 0;
        for (int x = 1; x <= m*2 && x <= size-start; x++) {
            curSum += arr[start+x-1];
            int oppositeMaxSum = dfs(start+x, Math.max(m, x));
            curMaxSum = Math.max(curMaxSum, curSum + suffixSum[start+x] - oppositeMaxSum);
        }

        return dp[start][m] = curMaxSum;
    }
}
