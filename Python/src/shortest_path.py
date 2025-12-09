# Dijkstra and Bellman-Ford
import heapq
from typing import List, Tuple

def dijkstra(n: int, adj: List[List[Tuple[int,int]]], src: int) -> List[float]:
    dist = [float('inf')]*n
    dist[src] = 0
    pq = [(0, src)]
    while pq:
        d,u = heapq.heappop(pq)
        if d > dist[u]: continue
        for v,w in adj[u]:
            nd = d + w
            if nd < dist[v]:
                dist[v] = nd
                heapq.heappush(pq, (nd, v))
    return dist

def bellman_ford(n: int, edges: List[Tuple[int,int,int]], src: int):
    dist = [float('inf')]*n
    dist[src] = 0
    for _ in range(n-1):
        updated = False
        for u,v,w in edges:
            if dist[u] != float('inf') and dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                updated = True
        if not updated:
            break
    # detect negative cycles (optional)
    for u,v,w in edges:
        if dist[u] != float('inf') and dist[u] + w < dist[v]:
            raise ValueError("Negative cycle detected")
    return dist
