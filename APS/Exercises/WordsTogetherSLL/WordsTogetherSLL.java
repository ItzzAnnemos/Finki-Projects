package mk.ukim.finki.ZadaciZaVezbanje.WordsTogetherSLL;

import java.util.Scanner;

public class WordsTogetherSLL {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        SLL<String> list1 = new SLL<>();
        n = Integer.parseInt(scan.nextLine());

        for(int i = 0; i<n; i++) {
            list1.insertLast(scan.nextLine());
        }

        putWordsTogether(list1);
    }

    public static void putWordsTogether(SLL<String> list) {
        SLLNode<String> tmp = list.getFirst();
        String word = "";
        SLL<String> newList = new SLL<>();

        while (tmp != null) {
            if (tmp.element.equals(",")) {
                newList.insertLast(word);
                word = "";
            } else {
                word += tmp.element;
            }
            tmp = tmp.succ;
        }

        if (!word.isEmpty()) {
            newList.insertLast(word);
        }

        System.out.println(list);
        System.out.println(newList);
    }
}
