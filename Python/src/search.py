# Recherche binaire
from typing import List, Optional

def binary_search(arr: List[int], target: int) -> Optional[int]:
    lo, hi = 0, len(arr)-1
    while lo <= hi:
        mid = (lo+hi)//2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            lo = mid+1
        else:
            hi = mid-1
    return None