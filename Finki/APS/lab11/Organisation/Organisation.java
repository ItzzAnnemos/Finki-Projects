package mk.ukim.finki.lab11.Organisation;

import java.util.*;

public class Organisation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int numConnections = Integer.parseInt(sc.nextLine());

        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();

        int sumCollaborations = 0;

        for (int i = 0;i < numConnections;i++) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");

            graph.addEdge(parts[1], parts[3], Integer.parseInt(parts[4]));
            sumCollaborations+= Integer.parseInt(parts[4]);
        }

        String president = sc.nextLine();

        List<String> members = new ArrayList<>(graph.shortestPath(president));
        members.add(president);
        Collections.sort(members);

        int optimalSum = Integer.MAX_VALUE;

        do {
            int currentSum = calculateSumCollaboration(graph, members);
            optimalSum = Math.min(optimalSum, currentSum);
        } while (nextPermutation(members));

        // Print the result
        System.out.println(optimalSum);
    }

    private static int calculateSumCollaboration(AdjacencyListGraph<String> graph, List<String> members) {
        int sumCollaboration = 0;

        for (int i = 0; i < members.size(); i++) {
            String member1 = members.get(i);

            for (int j = i + 1; j < members.size(); j++) {
                String member2 = members.get(j);

                sumCollaboration += graph.getWeight(member1, member2);
            }
        }

        return sumCollaboration;
    }

    private static boolean nextPermutation(List<String> list) {
        int i = list.size() - 2;

        while (i >= 0 && list.get(i).compareTo(list.get(i + 1)) >= 0) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = list.size() - 1;
        while (list.get(j).compareTo(list.get(i)) <= 0) {
            j--;
        }

        swap(list, i, j);

        i++;
        j = list.size() - 1;

        while (i < j) {
            swap(list, i, j);
            i++;
            j--;
        }

        return true;
    }

    private static void swap(List<String> list, int i, int j) {
        String temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
