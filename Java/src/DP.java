import java.util.*;

public class DP {

    public static int knapsack01(int[] w, int[] v, int W) {
        int[] dp = new int[W+1];
        for (int i=0;i<w.length;i++) {
            for (int cap=W; cap>=w[i]; cap--) {
                dp[cap] = Math.max(dp[cap], dp[cap-w[i]] + v[i]);
            }
        }
        return dp[W];
    }

    public static int lisLength(int[] arr) {
        ArrayList<Integer> t = new ArrayList<>();
        for (int x : arr) {
            int pos = Collections.binarySearch(t, x);
            if (pos < 0) pos = -pos - 1;
            if (pos == t.size()) t.add(x);
            else t.set(pos, x);
        }
        return t.size();
    }
}
