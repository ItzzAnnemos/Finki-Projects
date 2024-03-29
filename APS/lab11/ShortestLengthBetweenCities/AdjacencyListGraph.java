package mk.ukim.finki.lab11.ShortestLengthBetweenCities;

import java.util.*;

public class AdjacencyListGraph<T> {
    private Map<T, Map<T, Double>> adjacencyList;

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
        for (Map<T, Double> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination, double weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).put(destination, weight);
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

    public double getWeight(T source, T destination) {
        Map<T, Double> neighbors = adjacencyList.get(source);
        Double weight = 0.0;
        if (neighbors != null) {
            weight = neighbors.get(destination);
        }

        return weight;
    }


    public Map<T, Double> shortestPath(T startVertex) {
        Map<T, Double> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<T> explored = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Double> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                double newDest = distances.get(current) + neighborEntry.getValue();

                if (newDest < distances.get(neighbor)) {
                    distances.put(neighbor, newDest);

                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    public List<T> getRoute(T start, T end, Map<T, Double> distances) {
        List<T> route = new ArrayList<>();
        Map<T, T> predecessors = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        Set<T> explored = new HashSet<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (T neighbor : adjacencyList.getOrDefault(current, new HashMap<>()).keySet()) {
                if (!explored.contains(neighbor)) {
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }

        T current = end;
        while (current != null) {
            route.add(current);
            current = predecessors.get(current);
        }

        Collections.reverse(route);
        return route;
    }
}
