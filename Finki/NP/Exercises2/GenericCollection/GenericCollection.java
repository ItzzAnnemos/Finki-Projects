package mk.ukim.finki.NP.ZadaciZaVezbanje2.GenericCollection;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GenericCollection<T extends Comparable<T> & IHasTimestamp> {
    private Map<String, Set<T>> map;

    public GenericCollection() {
        this.map = new HashMap<>();
    }

    public void addGenericItem(String category, T element) {
        if (map.containsKey(category)) {
            map.get(category).add(element);
        } else {
            map.put(category, new HashSet<>());
            map.get(category).add(element);
        }
    }

    public Collection<T> findAllBetween(LocalDateTime from, LocalDateTime to) {
        return map.values().stream()
                .flatMap(Collection::stream)
                .filter(el -> el.getTimestamp().isAfter(from) && el.getTimestamp().isBefore(to))
                .collect(Collectors.toCollection(() -> new TreeSet<T>(Comparator.reverseOrder())));
    }

    public Collection<T> itemsFromCategories(List<String> categories) {
        return map.keySet().stream()
                .filter(categories::contains)
                .flatMap(category -> map.get(category)
                        .stream())
                .collect(Collectors.toCollection(() -> new TreeSet<T>(Comparator.reverseOrder())));
    }

    public Map<String, Set<T>> byMonthAndDay() {
        return map.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        element -> String.format("%02d-%02d",
                                element.getTimestamp().getMonthValue(),
                                element.getTimestamp().getDayOfMonth()),
                        TreeMap::new, Collectors.toCollection(() -> new TreeSet<T>(Comparator.reverseOrder()))
                ));
    }

    public Map<Integer, Long> countByYear() {
        return map.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        element -> element.getTimestamp().getYear(),
                        TreeMap::new, Collectors.counting()
                ));
    }
}
