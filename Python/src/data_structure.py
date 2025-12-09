# Fenwick (BIT) and Segment Tree (sum)
from typing import List

class Fenwick:
    def __init__(self, n):
        self.n = n
        self.bit = [0]*(n+1)
    def add(self, idx, val):
        i = idx+1
        while i <= self.n:
            self.bit[i] += val
            i += i & -i
    def sum(self, idx):
        i = idx+1
        s = 0
        while i > 0:
            s += self.bit[i]
            i -= i & -i
        return s
    def range_sum(self, l, r):
        return self.sum(r) - (self.sum(l-1) if l>0 else 0)

class SegmentTree:
    def __init__(self, arr: List[int]):
        n = len(arr)
        self.n = 1
        while self.n < n: self.n <<= 1
        self.tree = [0]*(2*self.n)
        for i in range(n):
            self.tree[self.n + i] = arr[i]
        for i in range(self.n-1, 0, -1):
            self.tree[i] = self.tree[2*i] + self.tree[2*i+1]
    def update(self, idx, val):
        i = self.n + idx
        self.tree[i] = val
        while i > 1:
            i //= 2
            self.tree[i] = self.tree[2*i] + self.tree[2*i+1]
    def query(self, l, r):
        # inclusive l,r
        l += self.n; r += self.n
        res = 0
        while l <= r:
            if l%2==1:
                res += self.tree[l]; l += 1
            if r%2==0:
                res += self.tree[r]; r -= 1
            l//=2; r//=2
        return res
