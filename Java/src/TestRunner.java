import java.util.*;

public class TestRunner {

    private static void assertEquals(Object a, Object b){
        if ((a==null && b!=null) || (a!=null && !a.equals(b)))
            throw new RuntimeException("Assertion failed: "+a+" != "+b);
    }
    private static void assertTrue(boolean cond){
        if (!cond) throw new RuntimeException("Assertion failed: expected true");
    }
    private static void assertArrayEquals(int[] a, int[] b){
        if (!Arrays.equals(a,b))
            throw new RuntimeException("Array assertion failed");
    }

    public static void main(String[] args){

        System.out.println("Running tests...");

        // Sorting
        int[] arr={5,2,9,1,5,6};
        int[] q=arr.clone(); Sorting.quickSort(q);
        assertArrayEquals(q,new int[]{1,2,5,5,6,9});
        assertArrayEquals(Sorting.mergeSort(arr), new int[]{1,2,5,5,6,9});
        assertArrayEquals(Sorting.insertionSort(arr), new int[]{1,2,5,5,6,9});

        // Search
        assertEquals(Search.binarySearch(new int[]{1,3,5,7},5),2);
        assertEquals(Search.binarySearch(new int[]{1,3,5,7},4),-1);

        // UnionFind
        UnionFind uf=new UnionFind(5);
        assertTrue(uf.union(0,1));
        assertEquals(uf.find(0), uf.find(1));

        // MST
        List<GraphMST.Edge> edges=new ArrayList<>();
        edges.add(new GraphMST.Edge(0,1,1));
        edges.add(new GraphMST.Edge(1,2,2));
        edges.add(new GraphMST.Edge(0,2,3));
        assertEquals(GraphMST.kruskal(3, edges).size(), 2);

        // DP
        assertEquals(DP.knapsack01(new int[]{2,3,4},new int[]{3,4,5},5),7);
        assertEquals(DP.lisLength(new int[]{3,1,2,4}),3);

        // Fenwick
        Fenwick f=new Fenwick(5);
        for (int i=0;i<5;i++) f.add(i,i+1);
        assertEquals(f.range(0,4),15L);

        System.out.println("All tests passed!");
    }
}
