package mk.ukim.finki.lab2.MElement;

import java.util.Scanner;

public class SpecialSLLDelete<E> {
    //TODO: implement method
    public void specialDelete(SLL<E> list, int m) {
        SLLNode<E> tmp = list.getFirst();
        SLLNode<E> prev = null;

        int i = 1;

        while (tmp != null) {
            if (i % m == 0) {
                if (prev == null) {
                    list.deleteFirst();
                    tmp = list.getFirst();
                } else {
                    prev.succ = tmp.succ;
                    tmp = prev.succ;
                }
            } else {
                prev = tmp;
                tmp = tmp.succ;
            }

            i++;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }

        int m = input.nextInt();

        SpecialSLLDelete<Integer> tmp = new SpecialSLLDelete<>();

        tmp.specialDelete(list, m);

        System.out.println(list);
    }
}
