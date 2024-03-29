package mk.ukim.finki.ZadaciZaVezbanje.MergeSLL;

import java.util.Scanner;

public class MergeSLL {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        SLL<Integer> list1 = new SLL<>();
        SLL<Integer> list2 = new SLL<>();
        n = scan.nextInt();

        for(int i = 0; i<n; i++) {
            list1.insertLast(scan.nextInt());
        }
        System.out.println("-------");
        for(int i = 0; i<n; i++) {
            list2.insertLast(scan.nextInt());
        }

        list1.merge(list2);
        System.out.println(list1);
    }
}
