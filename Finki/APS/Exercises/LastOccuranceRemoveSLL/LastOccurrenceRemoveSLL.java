package mk.ukim.finki.ZadaciZaVezbanje.LastOccuranceRemoveSLL;

import java.util.*;

public class LastOccurrenceRemoveSLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        removeLastOccurrence(list);
    }

    public static void removeLastOccurrence(SLL<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        SLLNode<Integer> tmp = list.getFirst();

        while (tmp != null) {
            if (map.containsKey(tmp.element)) {
                map.put(tmp.element, map.get(tmp.element) + 1);
            } else {
                map.put(tmp.element, 1);
            }
            tmp = tmp.succ;
        }

        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                set.add(entry.getKey());
            }
        }

        list.mirror();

        tmp = list.getFirst();
        while (tmp != null) {
            if (set.contains(tmp.element)) {
                list.delete(tmp);
                set.remove(tmp.element);
            }
            tmp = tmp.succ;
        }

        list.mirror();
        System.out.println(list);
    }
}
