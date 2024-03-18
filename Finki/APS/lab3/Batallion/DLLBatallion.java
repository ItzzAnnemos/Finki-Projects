package mk.ukim.finki.lab3.Batallion;

import java.util.Scanner;

public class DLLBatallion {
    //TODO: implement function
    public static void battalion(DLL<Integer> list, int a, int b) {
        DLLNode<Integer> nodeA = list.find(a);
        DLLNode<Integer> nodeB = list.find(b);

        DLL<Integer> tmp1 = new DLL<>();
        DLL<Integer> tmp2 = new DLL<>();
        DLL<Integer> tmp3 = new DLL<>();

        DLLNode<Integer> temp = list.getFirst();

        while (temp != null && !(temp.equals(nodeA))) {
            tmp1.insertLast(temp.element);
            if (temp.equals(nodeA)) {
                break;
            }
            temp = temp.succ;
        }

        while (temp != null) {
            tmp2.insertLast(temp.element);
            if (temp.equals(nodeB)) {
                break;
            }
            temp = temp.succ;
        }

        while (temp != null) {
            if (temp.succ == null) {
                break;
            }
            tmp3.insertLast(temp.succ.element);
            temp = temp.succ;
        }

        tmp2.mirror();

        list.deleteList();

        temp = tmp1.getFirst();
        for (int i = 0; i < tmp1.getSize(); i++) {
            list.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = tmp2.getFirst();
        for (int i = 0; i < tmp2.getSize(); i++) {
            list.insertLast(temp.element);
            temp = temp.succ;
        }

        temp = tmp3.getFirst();
        for (int i = 0; i < tmp3.getSize(); i++) {
            list.insertLast(temp.element);
            temp = temp.succ;
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();

        battalion(list, a, b);

        System.out.println(list);
        System.out.println(list.toStringR());


    }
}
