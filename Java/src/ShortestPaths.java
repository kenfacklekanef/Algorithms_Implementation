import java.util.*;

public class ShortestPaths {

    public static double[] dijkstra(int n, List<List<int[]>> adj, int src) {
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        pq.add(new double[]{src, 0});

        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int u = (int)cur[0];
            double d = cur[1];
            if (d > dist[u]) continue;
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    pq.add(new double[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    public static double[] bellmanFord(int n, List<int[]> edges, int src) {
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;

        for (int i=0;i<n-1;i++) {
            boolean updated = false;
            for (int[] e : edges) {
                int u=e[0], v=e[1], w=e[2];
                if (dist[u] != Double.POSITIVE_INFINITY && dist[u]+w < dist[v]) {
                    dist[v] = dist[u]+w;
                    updated=true;
                }
            }
            if (!updated) break;
        }

        return dist;
    }
}
