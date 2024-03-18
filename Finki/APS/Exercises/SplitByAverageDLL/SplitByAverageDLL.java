package mk.ukim.finki.ZadaciZaVezbanje.SplitByAverageDLL;

import java.util.Scanner;

public class SplitByAverageDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for (int i = 0;i < n;i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);
        splitByAverage(list);
    }

    public static void splitByAverage(DLL<Integer> list) {
        int sum = 0;
        DLLNode<Integer> tmp = list.getFirst();

        while (tmp != null) {
            sum += tmp.element;
            tmp = tmp.succ;
        }

        double avg = (double) sum / list.getSize();

        DLL<Integer> bellowAvg = new DLL<>();
        DLL<Integer> aboveAvg = new DLL<>();

        tmp = list.getLast();
        while (tmp != null) {
            if (tmp.element > avg) {
                aboveAvg.insertLast(tmp.element);
            } else {
                bellowAvg.insertLast(tmp.element);
            }
            tmp = tmp.pred;
        }

        /*aboveAvg.mirror();
        bellowAvg.mirror();*/

        System.out.println(bellowAvg);
        System.out.println(aboveAvg);
    }
}
