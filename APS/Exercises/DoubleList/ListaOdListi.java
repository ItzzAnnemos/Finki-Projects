package mk.ukim.finki.ZadaciZaVezbanje.DoubleList;

import java.util.Scanner;

public class ListaOdListi {
    public static long findMagicNumber(DLL<DLL<Integer>> list) {
        long sum = 0;
        long product = 1;
        DLLNode<DLL<Integer>> sub = list.getFirst();
        for (int i = 0; i < list.length(); i++) {
            sum = 0;
            DLLNode<Integer> tmp = sub.element.getFirst();
            for (int j = 0; j < sub.element.length(); j++) {
                sum += tmp.element;
                tmp = tmp.succ;
            }
            product *= sum;
            sub = sub.succ;
        }
        return product;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        DLL<DLL<Integer>> list = new DLL<DLL<Integer>>();
        for (int i = 0; i < n; i++) {
            DLL<Integer> tmp = new DLL<Integer>();
            for (int j = 0; j < m; j++) {
                tmp.insertLast(in.nextInt());
            }
            list.insertLast(tmp);
        }
        in.close();
        System.out.println(findMagicNumber(list));
    }

}
