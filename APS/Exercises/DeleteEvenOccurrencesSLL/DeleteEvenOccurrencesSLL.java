package mk.ukim.finki.ZadaciZaVezbanje.DeleteEvenOccurrencesSLL;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DeleteEvenOccurrencesSLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        deleteOccurrences(list);
    }

    public static void deleteOccurrences(SLL<Integer> list) {
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

        tmp = list.getFirst();
        while (tmp.succ != null) {
            if (map.containsKey(tmp.succ.element) && map.get(tmp.succ.element) % 2 == 0) {
                list.delete(tmp.succ);
            } else {
                tmp = tmp.succ;
            }
        }

        tmp = list.getFirst();
        if (map.containsKey(tmp.element) && map.get(tmp.element) % 2 == 0) {
            list.deleteFirst();
        }

        System.out.println(list);
    }
}
