import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

class Main {

    static int numVer; // the number of vertices in the graph
    static ArrayList<Integer> graph[]; // flow is computed on this graph
    static int[][] capacity, flow;

    // Compute a path with positive residual capacity using BFS
    // Return true if such a path exists and false otherwise
    public static boolean augmentingPathExists(int previousVertexOnPath[]) {
        // Allocate space for auxilary data structures
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[numVer];

        // Initialization of auxilary data structures
        for(int i = 0; i < numVer; i++) visited[i] = false;
        queue.add(0);
        visited[0] = true;

        // BFS
        while(!queue.isEmpty()) {
            int v = queue.poll();
            for(int w : graph[v]) {
                if (!visited[w] && capacity[v][w] > flow[v][w]) {
                    visited[w] = true;
                    previousVertexOnPath[w] = v;
                    queue.add(w);
                    if (w == numVer - 1) return true;
                }
            }
        }

        return false;
    }

    // Computes the value of a maximum flow
    public static int computeMaximumFlow() {
        int i, flowOnPath;
        // Find paths with BFS and return path in previousVertexOnPath array
        int[] previousVertexOnPath = new int[numVer];
        // Start with empty flow
        int maxFlow = 0;
        // Use augmenting path P as long as possible
        while(augmentingPathExists(previousVertexOnPath)) {
            // Compute smallest remaining capacity on P
            flowOnPath = Integer.MAX_VALUE;
            for(i = numVer-1; i != 0; i = previousVertexOnPath[i]) {
                int p = previousVertexOnPath[i];
                flowOnPath = Math.min(flowOnPath, capacity[p][i] - flow[p][i]);
            }
            // Add the smallest remaining capacity to each edge of P
            for(i = numVer-1; i != 0; i = previousVertexOnPath[i]) {
                int p = previousVertexOnPath[i];
                flow[p][i] += flowOnPath;
                flow[i][p] -= flowOnPath;
            }
            maxFlow += flowOnPath;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        // Create a new Scanner object for reading the input
        Scanner sc = new Scanner(System.in);

        // Read the number of testcases to follow
        int t = sc.nextInt();

        // Iterate over the testcases and solve the problem
        for (int i = 0; i < t; i++) {
            testCase(sc);
        }
    }

    public static void testCase(Scanner sc) {
        // Read the input

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        boolean occupied[] = new boolean[n*m + 1];

        // mark the occupied fields
        for(int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            int position = (y * n) + x + 1;

            occupied[position] = true;
        }

        // s + chessboard + t
        numVer = 1 + (n * m) + 1;

        // Don't forget to initialize numVer
        // Initialize the graph
        capacity = new int[numVer][numVer];
        flow = new int[numVer][numVer];

        graph = (ArrayList<Integer>[])new ArrayList[numVer];
        for(int i = 0; i < numVer; i++) {
            graph[i] = new ArrayList<Integer>();
        }




        // Compute maximum flow
        int maxFlow;
        maxFlow = computeMaximumFlow();

        System.out.println(maxFlow);

        // Output result
    }

    static public void addUnique(ArrayList<Integer> list, int i) {
        if(list.contains(i)) {
            return;
        }
        else {
            list.add(i);
        }
    }

}