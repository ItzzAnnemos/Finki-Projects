package mk.ukim.finki.ZadaciZaVezbanje.SplitDLL;

import java.util.Scanner;

public class SplitDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for (int i = 0;i < n;i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        reorder(list);
    }

    public static void reorder(DLL<Integer> list) {
        DLL<Integer> even = new DLL<>();
        DLL<Integer> uneven = new DLL<>();
        DLLNode<Integer> tmp = list.getFirst();

        while (tmp != null) {
            if (tmp.element % 2 == 0) {
                even.insertLast(tmp.element);
            } else {
                uneven.insertLast(tmp.element);
            }
            tmp = tmp.succ;
        }

        even.mirror();

        uneven.getLast().succ = even.getFirst();

        System.out.println(uneven);
    }
}
