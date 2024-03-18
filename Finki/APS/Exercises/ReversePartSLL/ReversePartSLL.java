package mk.ukim.finki.ZadaciZaVezbanje.ReversePartSLL;

import java.util.Scanner;

public class ReversePartSLL {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        SLL<Integer> list1 = new SLL<>();
        n = scan.nextInt();

        for(int i = 0; i<n; i++) {
            list1.insertLast(scan.nextInt());
        }

        int start = scan.nextInt();
        int end = scan.nextInt();

        System.out.println(list1);

        list1.reversePart(start, end);

        System.out.println(list1);
    }
}
