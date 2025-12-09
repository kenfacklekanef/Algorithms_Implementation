import sys
import os

# Ajoute le dossier parent (le dossier racine du projet) au chemin de recherche
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

import pytest
from src.sorting import quicksort, mergesort, insertion_sort
from src.search import binary_search
from src.graph_basic import bfs, dfs_iter, UnionFind
from src.graph_mst import kruskal, prim
from src.shortest_path import dijkstra, bellman_ford
from src.dp import knapsack_01, lis_length
from src.strings import kmp_search, Trie
from src.geometry import convex_hull
from src.data_structure import Fenwick, SegmentTree

def test_sorting():
    arr = [5,2,9,1,5,6]
    assert quicksort(arr) == sorted(arr)
    assert mergesort(arr) == sorted(arr)
    assert insertion_sort(arr) == sorted(arr)

def test_search():
    arr = [1,3,5,7,9]
    assert binary_search(arr,5) == 2
    assert binary_search(arr,2) is None

def test_bfs_dfs_unionfind():
    g = {0:[1,2], 1:[2], 2:[3], 3:[]}
    assert bfs(g,0)[0] == 0
    assert 0 in dfs_iter(g,0)
    uf = UnionFind(5)
    assert uf.union(0,1)
    assert uf.find(0) == uf.find(1)

def test_mst():
    edges = [(0,1,1),(1,2,2),(0,2,3)]
    mst = kruskal(3, edges)
    assert len(mst) == 2

def test_shortest_paths():
    adj = [[(1,1),(2,4)], [(2,2)], []]
    dist = dijkstra(3, adj, 0)
    assert dist[2] == 3
    edges = [(0,1,1),(1,2,2),(0,2,5)]
    assert bellman_ford(3, edges, 0)[2] == 3

def test_dp():
    assert knapsack_01([2,3,4],[3,4,5],5) == 7
    assert lis_length([3,1,2,4]) == 3

def test_strings_trie_kmp():
    assert kmp_search("ababcabc", "abc") == [2,5]
    t = Trie()
    t.insert("hello")
    assert t.search("hello")
    assert t.starts_with("he")

def test_geometry():
    pts = [(0,0),(1,0),(1,1),(0,1),(0.5,0.5)]
    hull = convex_hull(pts)
    assert set(hull) >= {(0,0),(1,0),(1,1),(0,1)}

def test_data_structures():
    bit = Fenwick(5)
    for i in range(5):
        bit.add(i, i+1)
    assert bit.range_sum(0,4) == 15
    st = SegmentTree([1,2,3,4])
    assert st.query(0,3) == 10
    st.update(2,10)
    assert st.query(0,3) == 17
