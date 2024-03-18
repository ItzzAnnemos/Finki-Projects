package mk.ukim.finki.lab10.AdjacencyMatrixGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdjacencyMatrixGraphTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            if (line.startsWith("ADDEDGE")) {
                String[] parts = line.split("\\s+");
                int vertex1 = Integer.parseInt(parts[1]);
                int vertex2 = Integer.parseInt(parts[2]);

                graph.addEdge(vertex1, vertex2);
                graph.addVertex(vertex1);
                graph.addVertex(vertex2);
            } else if (line.startsWith("DELETEEDGE")) {
                String[] parts = line.split("\\s+");
                int vertex1 = Integer.parseInt(parts[1]);
                int vertex2 = Integer.parseInt(parts[2]);

                graph.removeEdge(vertex1, vertex2);
            } else if (line.startsWith("ADJACENT")) {
                String[] parts = line.split("\\s+");
                int vertex1 = Integer.parseInt(parts[1]);
                int vertex2 = Integer.parseInt(parts[2]);

                if (graph.getNeighbors(vertex1).contains(vertex2)) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            } else if (line.startsWith("PRINTGRAPH")) {
                System.out.println(graph);
            }
        }
    }
}
