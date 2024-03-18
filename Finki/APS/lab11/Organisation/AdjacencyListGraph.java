package mk.ukim.finki.lab11.Organisation;

import java.util.*;

public class AdjacencyListGraph<T> {
    private Map<T, Map<T, Integer>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        for (Map<T, Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination, int weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).put(destination, weight);
        adjacencyList.get(destination).put(source, weight);
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>()).keySet();
    }

    public int getWeight(T source, T destination) {
        Map<T, Integer> neighbors = adjacencyList.get(source);
        Integer weight = 0;
        if (neighbors != null) {
            weight = neighbors.get(destination);
        }

        return (weight != null) ? weight : 0;
    }

    public Collection<T> shortestPath(T startVertex) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<T> explored = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Integer> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                int newDest = distances.get(current) + neighborEntry.getValue(); // Include collaboration degree

                if (newDest < distances.get(neighbor)) {
                    distances.put(neighbor, newDest);

                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances.keySet();
    }
}