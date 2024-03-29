package mk.ukim.finki.ZadaciZaVezbanje.RotateDLL;

import java.util.Scanner;

public class RotateDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i = 0;i < n;i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println("-------");

        int rotations = sc.nextInt();

        System.out.println(list);
        rotate(list, rotations);
    }

    public static void rotate(DLL<Integer> list, int rotations) {
        int k = rotations % list.getSize();

        DLLNode<Integer> tmp = null;

        for (int i = 0;i < k;i++) {
            tmp = list.getLast();
            list.deleteLast();
            list.insertFirst(tmp.element);
        }

        System.out.println(list);
    }
}
