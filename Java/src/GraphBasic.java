import java.util.*;

public class GraphBasic {

    public static List<Integer> bfs(Map<Integer, List<Integer>> g, int start) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        List<Integer> order = new ArrayList<>();
        q.add(start); seen.add(start);

        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : g.getOrDefault(u, Collections.emptyList())) {
                if (!seen.contains(v)) {
                    seen.add(v);
                    q.add(v);
                }
            }
        }
        return order;
    }

    public static List<Integer> dfsIter(Map<Integer, List<Integer>> g, int start) {
        Stack<Integer> st = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        List<Integer> order = new ArrayList<>();
        st.push(start);

        while (!st.empty()) {
            int u = st.pop();
            if (seen.contains(u)) continue;
            seen.add(u);
            order.add(u);
            List<Integer> neigh = g.getOrDefault(u, Collections.emptyList());
            for (int i = neigh.size()-1; i >= 0; i--)
                st.push(neigh.get(i));
        }
        return order;
    }
}
