import java.util.*;

/**
 * Implementation of Kruskal's Algorithm for finding Minimum Spanning Tree (MST)
 *
 * Overview:
 * This implementation finds a minimum spanning forest for an undirected, weighted graph
 * using Kruskal's greedy algorithm together with a Disjoint Set (Union-Find) data structure
 * to detect cycles efficiently.
 *
 * Algorithm (high-level):
 * 1. Collect all edges and sort them in non-decreasing order by weight.
 * 2. Initialize a disjoint set with one set per vertex.
 * 3. Iterate the sorted edges, and for each edge (u, v):
 *    a. If u and v lie in different sets (find(u) != find(v)), include the edge in the MST
 *       and union their sets.
 *    b. Otherwise, skip the edge (would create a cycle).
 * 4. Stop when you have added (V - 1) edges (for a connected graph) or when all edges are processed.
 *
 * Complexity:
 * - Sorting edges: O(E log E) (can be written as O(E log V) since E >= V in dense graphs)
 * - Union-Find operations: near O(α(V)) per operation with path compression and union by rank,
 *   where α is the inverse Ackermann function (practically constant).
 * - Overall: O(E log E) dominated by sorting.
 *
 * Notes and assumptions:
 * - Graph is assumed undirected. To represent an undirected edge add a single Edge(src,dest,weight).
 * - Vertex indices are expected to be in range [0, vertices-1].
 * - If the input graph is disconnected, the algorithm returns a minimum spanning forest
 *   (a collection of MSTs, one per connected component). The returned list size will be
 *   less than (V - 1) in that case.
 * - When multiple edges have equal weight, their relative order after sorting is arbitrary;
 *   this may lead to multiple valid MSTs with the same total weight.
 *
 * Usage:
 * - Construct Graph with number of vertices.
 * - Call addEdge(src, dest, weight) for each undirected edge.
 * - Call kruskalMST() to obtain the list of edges in the MST (or forest).
 *
 * Example:
 *   Graph g = new Graph(4);
 *   g.addEdge(0, 1, 10);
 *   g.addEdge(0, 2, 6);
 *   g.addEdge(0, 3, 5);
 *   g.addEdge(1, 3, 15);
 *   List<Edge> mst = g.kruskalMST();
 *
 */
public class KruskalMST {
    
    /**
     * Edge class represents a weighted edge in the graph
     */
    static class Edge implements Comparable<Edge> {
        int src;      // Source vertex
        int dest;     // Destination vertex
        int weight;   // Weight of the edge
        
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        
        // Compare edges based on weight (for sorting)
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
        
        @Override
        public String toString() {
            return src + " -- " + dest + " (weight: " + weight + ")";
        }
    }
    
    /**
     * Disjoint Set (Union-Find) data structure for cycle detection
     * Uses path compression and union by rank for efficiency
     */
    static class DisjointSet {
        int[] parent;  // Parent of each vertex
        int[] rank;    // Rank (approximate depth) of tree
        
        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            
            // Initially, each vertex is its own parent (separate set)
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        /**
         * Find the root/representative of the set containing vertex x
         * Uses path compression for efficiency
         */
        public int find(int x) {
            if (parent[x] != x) {
                // Path compression: make parent point directly to root
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        /**
         * Union two sets containing vertices x and y
         * Uses union by rank to keep tree balanced
         * Returns true if union was performed, false if already in same set
         */
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            // Already in the same set - would create a cycle
            if (rootX == rootY) {
                return false;
            }
            
            // Union by rank: attach smaller tree under root of larger tree
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            
            return true;
        }
    }
    
    /**
     * Graph class to represent the graph
     */
    static class Graph {
        int vertices;           // Number of vertices
        List<Edge> edges;       // List of all edges
        
        public Graph(int vertices) {
            this.vertices = vertices;
            this.edges = new ArrayList<>();
        }
        
        /**
         * Add an edge to the graph
         */
        public void addEdge(int src, int dest, int weight) {
            edges.add(new Edge(src, dest, weight));
        }
        
        /**
         * Find Minimum Spanning Tree using Kruskal's Algorithm
         * @return List of edges in the MST
         */
        public List<Edge> kruskalMST() {
            List<Edge> mst = new ArrayList<>();
            
            // Step 1: Sort all edges by weight
            Collections.sort(edges);
            
            // Create disjoint set for cycle detection
            DisjointSet ds = new DisjointSet(vertices);
            
            System.out.println("\n=== Kruskal's Algorithm Execution ===");
            System.out.println("Sorted edges:");
            for (Edge e : edges) {
                System.out.println("  " + e);
            }
            System.out.println();
            
            int edgeCount = 0;
            int edgeIndex = 0;
            
            // Step 2: Pick edges one by one until we have (V-1) edges
            while (edgeCount < vertices - 1 && edgeIndex < edges.size()) {
                Edge currentEdge = edges.get(edgeIndex++);
                
                // Check if adding this edge creates a cycle
                int srcRoot = ds.find(currentEdge.src);
                int destRoot = ds.find(currentEdge.dest);
                
                System.out.println("Processing edge: " + currentEdge);
                System.out.println("  Root of " + currentEdge.src + " = " + srcRoot);
                System.out.println("  Root of " + currentEdge.dest + " = " + destRoot);
                
                // If roots are different, adding this edge won't create cycle
                if (srcRoot != destRoot) {
                    mst.add(currentEdge);
                    // Perform union to merge sets
                    ds.union(currentEdge.src, currentEdge.dest);
                    edgeCount++;
                    System.out.println("  ✓ ADDED to MST (no cycle)");
                } else {
                    System.out.println("  ✗ REJECTED (would create cycle)");
                }
                System.out.println();
            }
            
            return mst;
        }
        
        /**
         * Display the graph
         */
        public void displayGraph() {
            System.out.println("\n=== Graph Structure ===");
            System.out.println("Vertices: " + vertices);
            System.out.println("Edges:");

            // Display all edges
            for (Edge e : edges) {
                System.out.println("  " + e);
            }
        }
    }
    
    /**
     * Display the Minimum Spanning Tree and calculate total weight
     */
    public static void displayMST(List<Edge> mst) {
        System.out.println("=== Minimum Spanning Tree ===");
        int totalWeight = 0;
        
        // Display all edges in the MST
        for (Edge e : mst) {
            System.out.println("  " + e);
            totalWeight += e.weight;
        }
        
        System.out.println("\nTotal edges in MST: " + mst.size());
        System.out.println("Total weight of MST: " + totalWeight);
    }
}
