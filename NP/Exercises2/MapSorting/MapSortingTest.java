package mk.ukim.finki.NP.ZadaciZaVezbanje2.MapSorting;

import java.util.*;

public class MapSortingTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        List<String> l = readMapPairs(scanner);
        if(n==1){
            Map<String, Integer> map = new HashMap<>();
            fillStringIntegerMap(l, map);
            SortedSet<Map.Entry<String, Integer>> s = entriesSortedByValues(map);
            System.out.println(s);
        } else {
            Map<Integer, String> map = new HashMap<>();
            fillIntegerStringMap(l, map);
            SortedSet<Map.Entry<Integer, String>> s = entriesSortedByValues(map);
            System.out.println(s);
        }
    }

    private static List<String> readMapPairs(Scanner scanner) {
        String line = scanner.nextLine();
        String[] entries = line.split("\\s+");
        return Arrays.asList(entries);
    }

    static void fillStringIntegerMap(List<String> l, Map<String,Integer> map) {
        l.stream()
                .forEach(s -> map.put(s.substring(0, s.indexOf(':')), Integer.parseInt(s.substring(s.indexOf(':') + 1))));
    }

    static void fillIntegerStringMap(List<String> l, Map<Integer, String> map) {
        l.stream()
                .forEach(s -> map.put(Integer.parseInt(s.substring(0, s.indexOf(':'))), s.substring(s.indexOf(':') + 1)));
    }

    private static <K, V> int getInsertionOrder(Map<K, V> map, K key) {
        List<K> keysList = new ArrayList<>(map.keySet());
        return keysList.indexOf(key);
    }

    static <K extends Comparable<K>, V extends Comparable<V>> SortedSet<Map.Entry<K , V>> entriesSortedByValues(Map<K, V> map) {
        Comparator<Map.Entry<K, V>> comparator = new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int value = o2.getValue().compareTo(o1.getValue());

                if (value == 0) {
                    return Integer.compare(getInsertionOrder(map, o1.getKey()), getInsertionOrder(map, o2.getKey()));
                }

                return value;
            }
        };

        SortedSet<Map.Entry<K, V>> sortedSet = new TreeSet<>(comparator);

        sortedSet.addAll(map.entrySet());

        System.out.println(map);

        return sortedSet;
    }
}
