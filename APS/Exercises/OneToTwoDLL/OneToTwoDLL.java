package mk.ukim.finki.ZadaciZaVezbanje.OneToTwoDLL;

import java.util.Scanner;

public class OneToTwoDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        split(list);
    }

    public static void split(DLL<Integer> list) {
        DLL<Integer> newList1 = new DLL<>();
        DLL<Integer> newList2 = new DLL<>();

        DLLNode<Integer> tmp = list.getFirst();

        while (tmp != null) {
            for (int i = 0;i < 2 && tmp != null;i++) {
                newList1.insertLast(tmp.element);
                tmp = tmp.succ;
            }

            for (int i = 0;i < 2 && tmp != null;i++) {
                newList2.insertLast(tmp.element);
                tmp = tmp.succ;
            }
        }

        System.out.println(newList1);
        System.out.println(newList2);
    }
}
