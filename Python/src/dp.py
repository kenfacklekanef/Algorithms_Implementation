# Dynamic programming: 0/1 knapsack and LIS
from typing import List

def knapsack_01(weights: List[int], values: List[int], W: int) -> int:
    n = len(weights)
    dp = [0]*(W+1)
    for i in range(n):
        w = weights[i]; v = values[i]
        for cap in range(W, w-1, -1):
            dp[cap] = max(dp[cap], dp[cap-w] + v)
    return dp[W]

def lis_length(arr: List[int]) -> int:
    # O(n log n) patience algorithm
    import bisect
    tails = []
    for x in arr:
        i = bisect.bisect_left(tails, x)
        if i == len(tails):
            tails.append(x)
        else:
            tails[i] = x
    return len(tails)
