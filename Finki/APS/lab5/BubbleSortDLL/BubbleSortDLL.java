package mk.ukim.finki.lab5.BubbleSortDLL;

import java.util.Scanner;

public class BubbleSortDLL {

    public static void BubbleSort(DLL<Integer> list) {
        int n = list.getSize();
        DLLNode<Integer> tmp;
        DLLNode<Integer> tmp2 = list.getFirst();

        for (int i = 0; i < n; i++) {
            tmp = tmp2;
            tmp2 = tmp2.succ;
            for (int j = 0; j < n - i - 1; j++) {
                if (tmp.element > tmp.succ.element) {
                    int temp = tmp.element;
                    tmp.element = tmp.succ.element;
                    tmp.succ.element = temp;
                }
                tmp = tmp.succ;
            }
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }

        BubbleSort(list);

        System.out.println(list);
    }
}
