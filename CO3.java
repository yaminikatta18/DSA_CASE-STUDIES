import java.util.*;

public class CO3 {

    private int vertices;
    private int[][] graph;

    public CO3(int v) {
        vertices = v;
        graph = new int[v][v];
    }

    // Add edge
    public void addEdge(int src, int dest, int weight) {
        graph[src][dest] = weight;
        graph[dest][src] = weight;
    }

    // BFS Traversal
    public void BFS(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int i = 0; i < vertices; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // DFS Traversal
    public void DFS(int start) {
        boolean[] visited = new boolean[vertices];

        System.out.print("DFS Traversal: ");
        DFSUtil(start, visited);
        System.out.println();
    }

    private void DFSUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < vertices; i++) {
            if (graph[node][i] != 0 && !visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }

    // Prim's MST
    public void primMST() {
        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        boolean[] mstSet = new boolean[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {

            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 &&
                        !mstSet[v] &&
                        graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent);
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void printMST(int[] parent) {

        String[] departments = {
                "Registration",
                "Consultation",
                "Vaccination",
                "Pharmacy",
                "Emergency"
        };

        int totalCost = 0;

        System.out.println("\nMinimum Spanning Tree (Prim's Algorithm):");
        System.out.println("Edge\t\t\tWeight");

        for (int i = 1; i < vertices; i++) {
            System.out.println(
                    departments[parent[i]] + " - " +
                            departments[i] + "\t\t" +
                            graph[i][parent[i]]
            );
            totalCost += graph[i][parent[i]];
        }

        System.out.println("\nTotal Minimum Cost = " + totalCost);
    }

    public static void main(String[] args) {

        CO3 petCare = new CO3(5);

        // PetCare Department Connections
        petCare.addEdge(0, 1, 4);
        petCare.addEdge(0, 2, 2);
        petCare.addEdge(1, 2, 1);
        petCare.addEdge(1, 3, 5);
        petCare.addEdge(2, 3, 8);
        petCare.addEdge(2, 4, 10);
        petCare.addEdge(3, 4, 2);

        System.out.println("==========================================");
        System.out.println(" PetCare Smart Veterinary Clinic System ");
        System.out.println("==========================================");

        petCare.BFS(0);
        petCare.DFS(0);

        petCare.primMST();
    }
}
