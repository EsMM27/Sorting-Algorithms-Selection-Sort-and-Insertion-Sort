/**
 * Tester class for Kruskal's MST Algorithm
 * Contains multiple test cases to demonstrate the algorithm
 */
public class KruskalTester {
    
    public static void main(String[] args) {
        
        // Run different test cases
        testCase1_SimpleGraph();
        testCase2_ComplexGraph();
        testCase3_DisconnectedGraph();
    }
    
    /**
     * Test Case 1: Simple graph with 4 vertices
     * 
     * Graph structure:
     *      0
     *     /|\
     *   10 6 5
     *   /  |  \
     *  1---2---3
     *     15
     */
    public static void testCase1_SimpleGraph() {
        System.out.println("test case 1: Simple Graph (4 vertices)");

        KruskalMST.Graph graph = new KruskalMST.Graph(4);
        
        // Add edges (vertex1, vertex2, weight)
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        
        graph.displayGraph();
        
        var mst = graph.kruskalMST();
        KruskalMST.displayMST(mst);
    }
    
    /**
     * Test Case 2: More complex graph with 9 vertices
     * Classic example used in many textbooks
     * 
     * Graph structure:
     *          0 --- 1 --- 2
     *          |   / |  \  |
     *          |  /  |   \ |
     *          | /   |    \|
     *          3 --- 4 --- 5
     *          |     |     |
     *          |     |     |
     *          6 --- 7 --- 8
     */
    public static void testCase2_ComplexGraph() {
        System.out.println("\n\ntest case 2: Complex Graph (9 vertices)");
        

        
        KruskalMST.Graph graph = new KruskalMST.Graph(9);
        
        // Add edges (creating a grid-like graph)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 3, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 11);
        graph.addEdge(1, 4, 7);
        graph.addEdge(1, 5, 4);
        graph.addEdge(2, 5, 2);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 6, 7);
        graph.addEdge(4, 5, 6);
        graph.addEdge(4, 7, 1);
        graph.addEdge(5, 8, 14);
        graph.addEdge(6, 7, 9);
        graph.addEdge(7, 8, 10);
        
        graph.displayGraph();
        
        var mst = graph.kruskalMST();
        KruskalMST.displayMST(mst);
    }
    
    /**
     * Test Case 3: Graph with potential cycle scenarios
     * Demonstrates how Kruskal's avoids cycles
     * 
     * Graph structure:
     *        1
     *       /|\
     *      2 3 4
     *       \|/
     *        5
     */
    public static void testCase3_DisconnectedGraph() {
        System.out.println("\n\ntest case 3: Triangle with Center (5 vertices)");
        
        KruskalMST.Graph graph = new KruskalMST.Graph(5);
        
        // Creating a graph where cycle detection is important
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 6);
        graph.addEdge(1, 2, 7);  // Will likely be rejected
        graph.addEdge(2, 3, 8);  // Will likely be rejected
        graph.addEdge(1, 3, 9);  // Will likely be rejected
        
        graph.displayGraph();
        
        var mst = graph.kruskalMST();
        KruskalMST.displayMST(mst);
    }
}
