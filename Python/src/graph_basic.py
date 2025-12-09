# BFS, DFS, Union-Find (disjoint set)
from collections import deque
from typing import Dict, List, Set

def bfs(graph: Dict[int, List[int]], start: int) -> List[int]:
    q = deque([start])
    visited = set([start])
    order = []
    while q:
        u = q.popleft()
        order.append(u)
        for v in graph.get(u, []):
            if v not in visited:
                visited.add(v)
                q.append(v)
    return order

def dfs_iter(graph: Dict[int, List[int]], start: int) -> List[int]:
    stack = [start]
    visited = set()
    order = []
    while stack:
        u = stack.pop()
        if u in visited: 
            continue
        visited.add(u)
        order.append(u)
        for v in reversed(graph.get(u, [])):
            if v not in visited:
                stack.append(v)
    return order

class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank = [0]*n
    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    def union(self, x, y):
        rx, ry = self.find(x), self.find(y)
        if rx == ry: return False
        if self.rank[rx] < self.rank[ry]:
            self.parent[rx] = ry
        elif self.rank[rx] > self.rank[ry]:
            self.parent[ry] = rx
        else:
            self.parent[ry] = rx
            self.rank[rx] += 1
        return True
