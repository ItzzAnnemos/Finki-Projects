package mk.ukim.finki.ZadaciZaVezbanje.EvenUnevenDLL;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EvenUnevenDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        reorder(list);
    }

    public static void reorder(DLL<Integer> list) {
        DLLNode<Integer> tmp = list.getFirst();
        Set<Integer> set = new HashSet<>();

        while (tmp != null) {
            if (set.contains(tmp.element)) {
                list.delete(tmp);
            } else {
                set.add(tmp.element);
            }
            tmp = tmp.succ;
        }

        tmp = list.getFirst();

        while (tmp.succ != null) {
            if (tmp.element > tmp.succ.element) {
                list.delete(tmp.succ);
            } else {
                tmp = tmp.succ;
            }
        }

        tmp = list.getFirst();

        while (tmp.succ != null) {
            if (tmp.element % 2 == 0 && tmp.succ.element % 2 == 0) {
                list.delete(tmp.succ);
            } else if (tmp.element % 2 == 1 && tmp.succ.element % 2 == 1) {
                list.delete(tmp.succ);
            } else {
                tmp = tmp.succ;
            }
        }

        System.out.println(list);
    }
}
