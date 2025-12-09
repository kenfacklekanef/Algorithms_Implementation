# KMP and Trie
from typing import List

def kmp_prefix(s: str) -> List[int]:
    n = len(s)
    pi = [0]*n
    for i in range(1,n):
        j = pi[i-1]
        while j > 0 and s[i] != s[j]:
            j = pi[j-1]
        if s[i] == s[j]: j += 1
        pi[i] = j
    return pi

def kmp_search(text: str, pattern: str) -> List[int]:
    if pattern == "": return list(range(len(text)+1))
    pi = kmp_prefix(pattern)
    res = []
    j = 0
    for i, ch in enumerate(text):
        while j > 0 and ch != pattern[j]:
            j = pi[j-1]
        if ch == pattern[j]:
            j += 1
            if j == len(pattern):
                res.append(i - j + 1)
                j = pi[j-1]
    return res

class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()
    def insert(self, word: str):
        node = self.root
        for ch in word:
            if ch not in node.children:
                node.children[ch] = TrieNode()
            node = node.children[ch]
        node.is_end = True
    def search(self, word: str) -> bool:
        node = self.root
        for ch in word:
            if ch not in node.children: return False
            node = node.children[ch]
        return node.is_end
    def starts_with(self, prefix: str) -> bool:
        node = self.root
        for ch in prefix:
            if ch not in node.children: return False
            node = node.children[ch]
        return True
