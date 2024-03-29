package mk.ukim.finki.lab11.ShortestLengthBetweenCities;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShortestLengthBetweenCities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int numPaths = Integer.parseInt(sc.nextLine());

        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();

        for (int i = 0;i < numPaths;i++) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");

            graph.addEdge(parts[1], parts[3], Double.parseDouble(parts[4]));
        }

        String start = sc.nextLine();
        String destination = sc.nextLine();

        Map<String, Double> distances = graph.shortestPath(start);
        Map<String, Double> distances2 = graph.shortestPath(destination);

        List<String> startToDestinationRoute = graph.getRoute(start, destination, distances2);
        List<String> destinationToStartRoute = graph.getRoute(destination, start, distances);

        double totalDistance = calculateTotalDistance(graph, startToDestinationRoute) +
                calculateTotalDistance(graph, destinationToStartRoute);

        printRoute(startToDestinationRoute);
        System.out.println();
        printRoute(destinationToStartRoute);
        System.out.println();
        System.out.printf("%.1f", totalDistance);
    }

    private static void printRoute(List<String> route) {
        for (int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i));
            if (i != route.size() - 1) {
                System.out.print(" ");
            }
        }
    }

    private static double calculateTotalDistance(AdjacencyListGraph<String> graph, List<String> route) {
        double totalDistance = 0.0;

        for (int i = 0; i < route.size() - 1; i++) {
            String currentCity = route.get(i);
            String nextCity = route.get(i + 1);

            totalDistance += graph.getWeight(currentCity, nextCity);
        }

        return totalDistance;
    }
}
