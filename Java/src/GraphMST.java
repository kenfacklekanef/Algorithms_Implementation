import java.util.*;

public class GraphMST {

    public static class Edge implements Comparable<Edge> {
        public int u,v,w;
        public Edge(int u,int v,int w) {this.u=u;this.v=v;this.w=w;}
        public int compareTo(Edge e){return Integer.compare(this.w, e.w);}
    }

    public static List<Edge> kruskal(int n, List<Edge> edges){
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                mst.add(e);
                if (mst.size()==n-1) break;
            }
        }
        return mst;
    }
}
