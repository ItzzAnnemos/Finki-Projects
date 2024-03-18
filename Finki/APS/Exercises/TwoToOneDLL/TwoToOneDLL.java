package mk.ukim.finki.ZadaciZaVezbanje.TwoToOneDLL;

import java.util.Scanner;

public class TwoToOneDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list1 = new DLL<>();
        DLL<Integer> list2 = new DLL<>();

        for (int i = 0; i < n; i++) {
            list1.insertLast(sc.nextInt());
        }

        for (int i = 0; i < n; i++) {
            list2.insertLast(sc.nextInt());
        }

        System.out.println(list1);
        System.out.println(list2);
        concat(list1, list2);
    }

    public static void concat(DLL<Integer> list1, DLL<Integer> lis2) {
        DLLNode<Integer> tmp = list1.getFirst();
        DLLNode<Integer> tmp2 = lis2.getFirst();

        DLL<Integer> newList = new DLL<>();

        while (tmp != null && tmp2 != null) {

            for (int i = 0;i < 3 && tmp != null;i++) {
                newList.insertLast(tmp.element);
                tmp = tmp.succ;
            }

            for (int i = 0;i < 3 && tmp2 != null;i++) {
                newList.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
        }

        if (tmp != null) {
            while (tmp != null) {
                newList.insertLast(tmp.element);
                tmp = tmp.succ;
            }
        }

        if (tmp2 != null) {
            while (tmp2 != null) {
                newList.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
        }

        System.out.println(newList);
    }
}
