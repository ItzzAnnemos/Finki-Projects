package mk.ukim.finki.ZadaciZaVezbanje.EvenElementSLL;

import java.util.Scanner;

public class DeleteSLL {
    public static void change(SLL<Integer> list, int br) {
        SLLNode<Integer> tmp = list.getFirst();
        SLLNode<Integer> first = list.getFirst();
        int count = 0, flag = 0;
        for (int i = 0;i < list.length();i++) {
            if (tmp.element == br) {
                count++;
                if (flag == 0) {
                    first = tmp;
                    flag = 1;
                }
            }
            tmp = tmp.succ;
        }
        if (count % 2 != 0) {
            list.insertBefore(br, first);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        int n, broj;
        SLL<Integer> list1 = new SLL<Integer>();
        n = scan.nextInt();
        for(int i = 0; i<n; i++) {
            list1.insertLast(scan.nextInt());
        }
        int br = scan.nextInt();
        change(list1,br);
        System.out.println(list1);


    }

}
