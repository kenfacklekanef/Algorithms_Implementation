# Kruskal and Prim
from typing import List, Tuple
import heapq
from .graph_basic import UnionFind

def kruskal(n: int, edges: List[Tuple[int,int,int]]) -> List[Tuple[int,int,int]]:
    # edges: list of (u,v,w)
    edges_sorted = sorted(edges, key=lambda x: x[2])
    uf = UnionFind(n)
    mst = []
    for u,v,w in edges_sorted:
        if uf.union(u,v):
            mst.append((u,v,w))
        if len(mst) == n-1:
            break
    return mst

def prim(n: int, adj: List[List[Tuple[int,int]]], start=0):
    # adj[u] = list of (v, weight)
    visited = [False]*n
    pq = [(0, start, -1)]  # (w, u, parent)
    mst = []
    while pq:
        w,u,parent = heapq.heappop(pq)
        if visited[u]: continue
        visited[u] = True
        if parent != -1:
            mst.append((parent, u, w))
        for v,wt in adj[u]:
            if not visited[v]:
                heapq.heappush(pq, (wt, v, u))
    return mst
