# Ford-Fulkerson via Edmonds-Karp (BFS-based)
from collections import deque
from typing import List

def edmonds_karp(capacity: List[List[int]], s: int, t: int) -> int:
    n = len(capacity)
    flow = 0
    parent = [-1]*n
    while True:
        # BFS to find augmenting path
        for i in range(n): parent[i] = -1
        parent[s] = -2
        q = deque()
        q.append((s, float('inf')))
        found = False
        while q:
            u, f = q.popleft()
            for v in range(n):
                if parent[v] == -1 and capacity[u][v] > 0:
                    parent[v] = u
                    newf = min(f, capacity[u][v])
                    if v == t:
                        flow += newf
                        # update residual
                        cur = v
                        while cur != s:
                            prev = parent[cur]
                            capacity[prev][cur] -= newf
                            capacity[cur][prev] += newf
                            cur = prev
                        found = True
                        break
                    q.append((v, newf))
            if found: break
        if not found: break
    return flow
