package mk.ukim.finki.ZadaciZaVezbanje.ZigZagSLL;

import java.util.Scanner;

public class ZigZagSLL {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        SLL<Integer> list1 = new SLL<>();
        n = scan.nextInt();

        for(int i = 0; i<n; i++) {
            list1.insertLast(scan.nextInt());
        }

        System.out.println(list1);
        makeZigZag(list1);
    }

    public static void makeZigZag(SLL<Integer> list) {
        SLLNode<Integer> tmp = list.getFirst();

        while (tmp != null) {
            if (tmp.element == 0) {
                list.delete(tmp);
            }
            tmp = tmp.succ;
        }

        tmp = list.getFirst();
        while (tmp.succ != null) {
            if (tmp.element > 0 && tmp.succ.element > 0) {
                list.delete(tmp.succ);
            } else if (tmp.element < 0 && tmp.succ.element < 0) {
                list.insertAfter(Math.abs(tmp.element) , tmp);
            } else {
                tmp = tmp.succ;
            }
        }

        System.out.println(list);
    }
}
