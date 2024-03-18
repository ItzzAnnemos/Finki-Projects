package mk.ukim.finki.ZadaciZaVezbanje.OneToTenSLL;

import java.util.ArrayList;
import java.util.Scanner;

public class OneToTenSLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        split(list);
    }

    public static void split(SLL<Integer> list) {
        int size = list.length() / 10;
        int bigger = list.length() % 10;

        ArrayList<SLL<Integer>> arr = new ArrayList<>();

        for (int i = 0;i < 10;i++) {
            arr.add(new SLL<>());
        }

        SLLNode<Integer> tmp = list.getFirst();

        for (int i = 0;i < bigger;i++) {
            for (int j = 0;j < size + 1 && tmp != null;j++) {
                arr.get(i).insertLast(tmp.element);
                tmp = tmp.succ;
            }
        }

        for (int i = bigger;i < 10;i++) {
            for (int j = 0;j < size && tmp != null;j++) {
                arr.get(i).insertLast(tmp.element);
                tmp = tmp.succ;
            }
        }

        System.out.println(arr);
    }
}
