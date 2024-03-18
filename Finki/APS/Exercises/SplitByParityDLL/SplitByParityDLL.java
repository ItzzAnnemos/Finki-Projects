package mk.ukim.finki.ZadaciZaVezbanje.SplitByParityDLL;

import java.util.Scanner;

public class SplitByParityDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for (int i = 0;i < n;i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        splitByParity(list);
    }

    public static void splitByParity(DLL<Integer> list) {
        int count = 1;
        DLLNode<Integer> tmpFromStart = list.getFirst();
        DLLNode<Integer> tmpFromEnd = list.getLast();

        DLL<Integer> even = new DLL<>();
        DLL<Integer> uneven = new DLL<>();

        while (count <= list.getSize() && tmpFromStart != null && tmpFromEnd != null) {
            if (count % 2 == 0) {
                if (tmpFromEnd.element % 2 == 0) {
                    even.insertLast(tmpFromEnd.element);
                } else {
                    uneven.insertLast(tmpFromEnd.element);
                }
                tmpFromEnd = tmpFromEnd.pred;
            } else {
                if (tmpFromStart.element % 2 == 0) {
                    even.insertLast(tmpFromStart.element);
                } else {
                    uneven.insertLast(tmpFromStart.element);
                }
                tmpFromStart = tmpFromStart.succ;
            }
            count++;
        }

        System.out.println(even);
        System.out.println(uneven);
    }
}
