package mk.ukim.finki.NP.ZadaciZaVezbanje2.Names;

import java.util.*;
import java.util.stream.Collectors;

public class Names {
    private Map<String, Integer> mapByName;

    public Names() {
        this.mapByName = new TreeMap<>(Comparator.naturalOrder());
    }

    public void addName(String name) {
        mapByName.put(name, mapByName.getOrDefault(name, 0) + 1);
    }

    public void printN(int n) {
        mapByName.entrySet().stream()
                .filter(entry -> entry.getValue() >= n)
                .forEach(entry -> {
                    System.out.printf("%s (%d) %d%n", entry.getKey(), entry.getValue(), removeDuplicates(entry.getKey()).length());
                });
    }

    public String findName(int len, int x) {
        List<String> list = mapByName.keySet().stream()
                .filter(name -> name.length() < len)
                .collect(Collectors.toList());

        x = x % list.size();
        return list.get(x);
    }

    public static String removeDuplicates(String input) {
        Set<Character> uniqueChars = new HashSet<>();
        StringBuilder resultBuilder = new StringBuilder();

        for (char ch : input.toLowerCase().toCharArray()) {
            if (uniqueChars.add(ch)) {
                resultBuilder.append(ch);
            }
        }

        return resultBuilder.toString();
    }
}
