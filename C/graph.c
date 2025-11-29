#include "graph.h"

// --- Outils pour Kruskal ---
typedef struct Edge {
    int src, dest, weight;
} Edge;

typedef struct Graph {
    int V, E;
    Edge* edges;
} Graph;

typedef struct Subset {
    int parent;
    int rank;
} Subset;

int find(Subset subsets[], int i) {
    if (subsets[i].parent != i)
        subsets[i].parent = find(subsets, subsets[i].parent);
    return subsets[i].parent;
}

void Union(Subset subsets[], int x, int y) {
    int xroot = find(subsets, x);
    int yroot = find(subsets, y);
    if (subsets[xroot].rank < subsets[yroot].rank) subsets[xroot].parent = yroot;
    else if (subsets[xroot].rank > subsets[yroot].rank) subsets[yroot].parent = xroot;
    else { subsets[yroot].parent = xroot; subsets[xroot].rank++; }
}

int myComp(const void* a, const void* b) {
    Edge* a1 = (Edge*)a;
    Edge* b1 = (Edge*)b;
    return a1->weight > b1->weight;
}

// --- Kruskal ---
void demoKruskal() {
    printf("\n--- Optimisation Kruskal (MST) ---\n");
    int V = 4, E = 5;
    Graph* graph = (Graph*)malloc(sizeof(Graph));
    graph->V = V; graph->E = E;
    graph->edges = (Edge*)malloc(E * sizeof(Edge));

    // Données Hardcodées pour la démo
    graph->edges[0] = (Edge){0, 1, 10};
    graph->edges[1] = (Edge){0, 2, 6};
    graph->edges[2] = (Edge){0, 3, 5};
    graph->edges[3] = (Edge){1, 3, 15};
    graph->edges[4] = (Edge){2, 3, 4};

    qsort(graph->edges, graph->E, sizeof(graph->edges[0]), myComp);

    Subset* subsets = (Subset*)malloc(V * sizeof(Subset));
    for (int v = 0; v < V; ++v) { subsets[v].parent = v; subsets[v].rank = 0; }

    int e = 0, i = 0;
    while (e < V - 1 && i < graph->E) {
        Edge next_edge = graph->edges[i++];
        int x = find(subsets, next_edge.src);
        int y = find(subsets, next_edge.dest);
        if (x != y) {
            printf(" -> Liaison Serveur %d -- Serveur %d (Cout: %d)\n", next_edge.src, next_edge.dest, next_edge.weight);
            Union(subsets, x, y);
            e++;
        }
    }
    free(subsets); free(graph->edges); free(graph);
}

// --- Dijkstra ---
int minDistance(int dist[], int sptSet[]) {
    int min = INF, min_index;
    for (int v = 0; v < MAX_SERVERS; v++)
        if (sptSet[v] == 0 && dist[v] <= min) min = dist[v], min_index = v;
    return min_index;
}

void demoDijkstra() {
    int graph[MAX_SERVERS][MAX_SERVERS] = {
        { 0, 10,  0, 30, 100 }, {10,  0, 50,  0,   0 }, { 0, 50,  0, 20,  10 },
        {30,  0, 20,  0,  60 }, {100, 0, 10, 60,   0 }
    };
    printf("\n--- Latences Dijkstra (Depuis Serveur 0) ---\n");
    int dist[MAX_SERVERS], sptSet[MAX_SERVERS];
    for (int i = 0; i < MAX_SERVERS; i++) dist[i] = INF, sptSet[i] = 0;
    dist[0] = 0;

    for (int count = 0; count < MAX_SERVERS - 1; count++) {
        int u = minDistance(dist, sptSet);
        sptSet[u] = 1;
        for (int v = 0; v < MAX_SERVERS; v++)
            if (!sptSet[v] && graph[u][v] && dist[u] != INF && dist[u] + graph[u][v] < dist[v])
                dist[v] = dist[u] + graph[u][v];
    }
    for (int i = 0; i < MAX_SERVERS; i++) printf(" -> Vers Serveur %d : %d ms\n", i, dist[i]);
}